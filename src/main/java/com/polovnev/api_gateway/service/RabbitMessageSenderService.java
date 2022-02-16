package com.polovnev.api_gateway.service;


import com.polovnev.api_gateway.dto.CountryDto;

public interface RabbitMessageSenderService {

    void sendMessageAddCountry(CountryDto countryDto);

    void sendMessageDeleteCountry(Long id);
}
