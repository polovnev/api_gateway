package com.polovnev.api_gateway.service;

public interface NotificationService {

    void sendEmail(String email, String activationCode);
}
