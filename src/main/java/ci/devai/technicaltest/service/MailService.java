package ci.devai.technicaltest.service;

import ci.devai.technicaltest.domain.Test;
import ci.devai.technicaltest.service.dto.TestDTO;

public interface MailService {
    void sendResultMail(Test test);
}
