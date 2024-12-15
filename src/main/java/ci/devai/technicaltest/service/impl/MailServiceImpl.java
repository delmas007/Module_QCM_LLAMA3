package ci.devai.technicaltest.service.impl;

import ci.devai.technicaltest.domain.Test;
import ci.devai.technicaltest.service.MailService;
import ci.devai.technicaltest.service.dto.TestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.format.DateTimeFormatter;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public MailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }
    @Override
    public void sendResultMail(Test testDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("noreply@delmas-gpt.tech");
            helper.setTo(testDTO.getMail());
            helper.setSubject("Résultats du Test" );
            Context context = new Context();
            context.setVariable("formattedStartDate", testDTO.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            context.setVariable("formattedEndDate", testDTO.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            context.setVariable("infoTest", testDTO);
            context.setVariable("resultats", testDTO.getResults());

            String body = templateEngine.process("result.html", context);
            helper.setText(body, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }
}
