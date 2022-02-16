package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.dto.CountryDto;
import com.polovnev.api_gateway.service.RabbitMessageSenderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageSenderServiceImpl implements RabbitMessageSenderService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMessageSenderServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessageAddCountry(CountryDto countryDto) {
    //    rabbitTemplate.convertAndSend("country","create", countryDto);
    }

    @Override
    public void sendMessageDeleteCountry(Long id) {
    //    rabbitTemplate.convertAndSend("country","delete", id);
    }
}
