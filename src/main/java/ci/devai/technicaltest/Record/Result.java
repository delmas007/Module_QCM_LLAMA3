package ci.devai.technicaltest.Record;

import ci.devai.technicaltest.domain.enumeration.AnswerStatus;

public record Result(Long id, String question, String answer, String correctAnswer, AnswerStatus answerStatus) {
}
