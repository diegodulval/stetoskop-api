package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.User;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendConfirmationEmail(User obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromUser(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromUser(User obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getEmail());
        sm.setFrom(sender);
        sm.setSubject("QR-CODE Prescritor - Cadastro confirmado!");

        StringBuilder message = new StringBuilder();
        message.append("Olá, seu cadastro foi efetuado com sucesso! <br /> \n");
        message.append("Nome: " + obj.getName() + "<br /> \n");
        message.append("Email: " + obj.getEmail() + "<br /> \n");

        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(message.toString());
        return sm;
    }
}
