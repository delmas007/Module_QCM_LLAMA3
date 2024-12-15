package ci.devai.technicaltest.service.dto;
import ci.devai.technicaltest.domain.Test;
import ci.devai.technicaltest.domain.enumeration.AnswerStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDTO {

    private Long id;

    private String question;

    private String answer;

    private String correctAnswer;

    private AnswerStatus answerStatus;

    @JsonIgnore
    private TestDTO test;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public AnswerStatus getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(AnswerStatus answerStatus) {
        this.answerStatus = answerStatus;
    }


    public TestDTO getTest() {
        return test;
    }

    public void setTest(TestDTO test) {
        this.test = test;
    }
}
