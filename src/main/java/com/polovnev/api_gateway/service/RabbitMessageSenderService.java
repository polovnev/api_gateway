package com.polovnev.api_gateway.service;


import com.polovnev.api_gateway.dto.CountryDto;
import com.polovnev.api_gateway.dto.QuestionDto;

public interface RabbitMessageSenderService {

    void sendMessageAddCountry(CountryDto countryDto);

    void sendMessageDeleteCountry(Long id);

    void sendMessageCreateQuestion(QuestionDto questionDto);
}
