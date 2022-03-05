package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.service.NotificationService;
import org.springframework.stereotype.Service;



@Service
public class NotificationServiceImpl implements NotificationService {


    @Override
    public void sendEmail(String email, String activationCode) {
        System.out.println("\n email: " + email + "\n activationCode: " + activationCode);
    }
}
