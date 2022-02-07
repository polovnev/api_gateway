package com.polovnev.country.service;


import com.polovnev.country.dto.CountryDto;

public interface RabbitMessageSenderService {

    void sendMessageAddCountry(CountryDto countryDto);

    void sendMessageDeleteCountry(Long id);
}
