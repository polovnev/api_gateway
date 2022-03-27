package com.polovnev.api_gateway.service;

import jakarta.mail.MessagingException;

public interface EmailSenderService {

     void sendApproveAccountEmail(String destinationEmail, String activationCode, String username) throws MessagingException;
}
