package ci.devai.technicaltest.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private String question;
    private PropostionReponseDTO propostionReponse;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

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
