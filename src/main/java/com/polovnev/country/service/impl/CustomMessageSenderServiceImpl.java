package com.polovnev.country.service.impl;

import com.polovnev.country.dto.CountryDto;
import com.polovnev.country.service.CustomMessageSenderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageSenderServiceImpl implements CustomMessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    public CustomMessageSenderServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(CountryDto countryDto) {
        rabbitTemplate.convertAndSend("country","create", countryDto);
    }
}
