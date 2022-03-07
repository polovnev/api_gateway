package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.dto.CountryDto;
import com.polovnev.api_gateway.dto.QuestionDto;
import com.polovnev.api_gateway.dto.ResponseDto;
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

    @Override
    public void sendMessageCreateQuestion(QuestionDto questionDto) {
        rabbitTemplate.convertAndSend("question", "create", questionDto);
    }

    @Override
    public void sendMessageCreateResponse(ResponseDto responseDto) {
        rabbitTemplate.convertAndSend("response", "create", responseDto);
    }
}
