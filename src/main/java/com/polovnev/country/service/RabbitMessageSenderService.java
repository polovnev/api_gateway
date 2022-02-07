package com.polovnev.country.service;


import com.polovnev.country.dto.CountryDto;

public interface RabbitMessageSenderService {

    void sendMessage(CountryDto countryDto);
}
