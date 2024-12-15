package ci.devai.technicaltest.service.mapping;


import ci.devai.technicaltest.domain.Result;
import ci.devai.technicaltest.service.dto.ResultDTO;

public class ResultMapping {

    public static ResultDTO toDto(Result result) {
        if (result == null) {
            return null;
        }

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.getId());
        resultDTO.setQuestion(result.getQuestion());
        resultDTO.setAnswer(result.getAnswer());
        resultDTO.setCorrectAnswer(result.getCorrectAnswer());
        resultDTO.setAnswerStatus(result.getAnswerStatus());

        return resultDTO;
    }

    public static ResultDTO toDtoRecord(ci.devai.technicaltest.Record.Result result) {
        if (result == null) {
            return null;
        }

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.id());
        resultDTO.setQuestion(result.question());
        resultDTO.setAnswer(result.answer());
        resultDTO.setCorrectAnswer(result.correctAnswer());
        resultDTO.setAnswerStatus(result.answerStatus());

        return resultDTO;
    }

    public static Result toEntity(ResultDTO resultDTO) {
        if (resultDTO == null) {
            return null;
        }

        Result result = new Result();
        result.setId(resultDTO.getId());
        result.setQuestion(resultDTO.getQuestion());
        result.setAnswer(resultDTO.getAnswer());
        result.setCorrectAnswer(resultDTO.getCorrectAnswer());
        result.setAnswerStatus(resultDTO.getAnswerStatus());
//        result.setVersion(0L);

        return result;
    }
}
