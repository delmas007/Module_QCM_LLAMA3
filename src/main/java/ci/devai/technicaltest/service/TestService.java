package ci.devai.technicaltest.service;



import ci.devai.technicaltest.Record.ListResponse;
import ci.devai.technicaltest.service.dto.*;

import java.util.List;

public interface TestService {
    List<QuestionDTO> generateQuestions(String level, String sector);

//    List<ResultDTO> responses(List<ResponseDTO> responses, TestDTO testDTO);
    List<ResultDTO> responses(ListResponse responses);
}
