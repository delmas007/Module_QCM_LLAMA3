package ci.devai.technicaltest.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {

    private String question;

    private PropostionReponseDTO propostionReponse;

    public PropostionReponseDTO getPropostionReponse() {
        return propostionReponse;
    }

    public void setPropostionReponse(PropostionReponseDTO propostionReponse) {
        this.propostionReponse = propostionReponse;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
