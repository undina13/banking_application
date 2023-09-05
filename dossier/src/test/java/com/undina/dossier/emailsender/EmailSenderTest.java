package com.undina.dossier.emailsender;


import com.undina.dossier.model.EmailMessage;
import com.undina.dossier.model.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailSenderTest {
    @InjectMocks
    EmailSender emailSender;

    @Mock
    JavaMailSender javaMailSender;
    @BeforeEach
    void before() {
        ReflectionTestUtils.setField(emailSender, "username", "username@mail.ru");

    }
    @Test
    void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("username@mail.ru");
        message.setTo("undina13@bk.ru");
        message.setSubject(Theme.APPLICATION_DENIED.toString());
        message.setText("some text");

        emailSender.sendMessage(new EmailMessage("undina13@bk.ru",  Theme.APPLICATION_DENIED,
                132L, "some text"));
        verify(javaMailSender, times(1)).send(message);
    }
}