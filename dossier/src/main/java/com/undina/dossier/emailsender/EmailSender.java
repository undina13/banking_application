package com.undina.dossier.emailsender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.EmailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class EmailSender {
    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender javaMailSender;
    public void sendMessage(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        log.info("create");
        message.setFrom(username);
        message.setTo(emailMessage.getAddress());
        message.setSubject(emailMessage.getTheme().toString());
        message.setText(emailMessage.getText());
        log.info("start sending");
        javaMailSender.send(message);
        log.info("end");
    }

}
