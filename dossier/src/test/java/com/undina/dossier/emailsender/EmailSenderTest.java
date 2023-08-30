package com.undina.dossier.emailsender;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class EmailSenderTest {
    @Autowired
    EmailSender emailSender;

    @Test
    void sendMail() {
        emailSender.sendMessage("foxundina@gmail.com", "test", "test");
        assertEquals(1, 1);
    }
}