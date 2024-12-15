package ci.devai.technicaltest.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ListResponse {
    private List<ResponseDTO> responses;
    private TestDTO test;

    public List<ResponseDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseDTO> responses) {
        this.responses = responses;
    }

    public TestDTO getTest() {
        return test;
    }

    public void setTest(TestDTO test) {
        this.test = test;
    }
}
