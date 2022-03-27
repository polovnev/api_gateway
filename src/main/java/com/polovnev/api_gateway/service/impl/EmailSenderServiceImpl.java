package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.service.EmailSenderService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Value("${notification.email.from}")
    private String from;

    @Value("${notification.email.secret-code}")
    private String secretCode;


    @Override
    public void sendApproveAccountEmail(String destinationEmail, String activationCode)
            throws MessagingException {
        Properties properties = getEmailProperties();
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, secretCode);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail));
        message.setSubject("Подтвердите регистрацию!");

        message.setText("<h1>Приветствую!</h1><br>" +
                " Для завершения регестрации перейдите по ссылке " +
                "http://localhost:8080/confirmRegistration/" + activationCode);

        Transport.send(message);
    }

    private Properties getEmailProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return properties;
    }
}
