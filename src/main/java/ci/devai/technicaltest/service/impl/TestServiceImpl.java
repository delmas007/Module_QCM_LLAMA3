package ci.devai.technicaltest.service.impl;

import ci.devai.technicaltest.Record.ListResponse;
import ci.devai.technicaltest.Record.Response;
import ci.devai.technicaltest.Repository.ResultRepository;
import ci.devai.technicaltest.Repository.TestRepository;
import ci.devai.technicaltest.domain.Result;
import ci.devai.technicaltest.domain.Test;
import ci.devai.technicaltest.domain.enumeration.AnswerStatus;
import ci.devai.technicaltest.mapping.ResultMapping;
import ci.devai.technicaltest.mapping.TestMapping;
import ci.devai.technicaltest.service.MailService;
import ci.devai.technicaltest.service.TestService;
import ci.devai.technicaltest.service.dto.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    private final ChatClient chatClient;
    private final TestRepository testRepository;
    private final ResultRepository resultRepository;
    private final MailService mailService;

    public TestServiceImpl(ChatClient.Builder builder, TestRepository testRepository, ResultRepository resultRepository,MailService mailService) {
        this.chatClient = builder.build();
        this.testRepository = testRepository;
        this.resultRepository = resultRepository;
        this.mailService = mailService;
    }

    @Override
    public List<QuestionDTO> generateQuestions(String level, String sector) {
        String prompt =
            "Génère 2 questions aléatoires sur le sujet suivant : " +
            sector +
            ". Chaque question doit avoir trois réponses possibles, et un niveau de difficulté " +
            level +
            ".";

        String systemMessage =
            "\"Vous êtes un générateur de questions à choix multiples (QCM) en français. Chaque question doit avoir trois réponses possibles, dont une correcte clairement définie \n" +
                    "et deux incorrectes mais plausibles. Chaque réponse doit être précise, factuelle et non ambiguë.\" \n";

        ResponseEntity<ChatResponse, List<QuestionDTO>> response = chatClient
            .prompt()
            .system(systemMessage)
            .user(prompt)
            .call()
            .responseEntity(new ParameterizedTypeReference<>() {});

        return response.entity();
    }

    @Override
    public List<ResultDTO> responses(ListResponse listResponse) {
        StringBuilder promptBuilder = new StringBuilder("La liste des questions et des réponses est la suivante :\n");
        for (Response response : listResponse.responses()) {
            promptBuilder.append("Question: ").append(response.question()).append("\n");
            promptBuilder.append("Réponse du candidat: ").append(response.response()).append("\n");
            promptBuilder.append("Propositions :\n");
            promptBuilder.append("1) ").append(response.propostionReponse().reponse1()).append("\n");
            promptBuilder.append("2) ").append(response.propostionReponse().reponse2()).append("\n");
            promptBuilder.append("3) ").append(response.propostionReponse().reponse3()).append("\n");
        }
        String prompt = promptBuilder.toString();

        String systemMessage =
                "Tu es un correcteur de QCM. À partir des questions reçues sous forme JSON, " +
                        "avec les réponses possibles et la réponse donnée par le candidat, " +
                        "retourne pour chaque question : " +
                        "1) La question, " +
                        "2) La réponse du candidat, " +
                        "3) La réponse correcte en texte parmi les propositions fournies (par exemple : 'Un langage objet', 'Il traduit le code en machine', etc.), " +
                        "4) Le statut de la réponse ('CORRECT' si la réponse du candidat correspond à la proposition correcte, sinon 'INCORRECT'). " +
                        "La réponse correcte doit être l'une des propositions listées dans 'propostionReponse'. " +
                        "Elle doit être choisie parmi les options proposées en fonction de la question. " +
                        "Ne génère pas de réponse correcte inventée ou modifiée. Assure-toi que la réponse correcte correspond exactement à l'une des options données (par exemple, 'Un langage objet'). " +
                        "Ignore toute information non pertinente, comme les métadonnées du test.";

        System.out.println("Prompt envoyé au modèle : " + prompt);

        ResponseEntity<ChatResponse, List<ResultDTO>> response = chatClient
                .prompt()
                .system(systemMessage)
                .user(prompt)
                .call()
                .responseEntity(new ParameterizedTypeReference<>() {});

        List<ResultDTO> resultDTOList = response.entity().stream()
                .map(result -> {
                    result.setCorrectAnswer(result.getCorrectAnswer().replaceFirst("^\\d+\\)\\s*", ""));
                    return result;
                })
                .collect(Collectors.toList());


        TestDTO testDTO = TestMapping.toDtoRecord(listResponse.test());
        Test save = testRepository.save(TestMapping.toEntity(testDTO));
        resultDTOList.forEach(resultDTO -> {
            Result entity = ResultMapping.toEntity(resultDTO);
            entity.setId(null);
            entity.setTest(save);
            resultRepository.save(entity);
        });
        sendResultMail(save);
        return resultDTOList;
    }

    @Async
    public void sendResultMail(Test save) {
        Optional<Test> testOptional = testRepository.findById(save.getId());
        List<Result> byTestId = resultRepository.findByTestId(save.getId());
        if (testOptional.isPresent()){
            Test test = testOptional.get();
            test.setResults(byTestId);
            mailService.sendResultMail(test);
        }
    }
}
