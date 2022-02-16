package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.CountryDto;
import com.polovnev.api_gateway.service.RabbitMessageSenderService;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class CountryFacade {

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Autowired
    private RabbitMessageSenderService rabbitMessageSenderService;


    public ResponseEntity<String> findAll() throws URISyntaxException {
        return restMessageSenderService.findAllCountries();
    }

    public CountryDto findById(Long id) {
        return null;
    }

    public CountryDto addCountry(CountryDto countryDto) {
        return null;
    }

    public void addCountryRabbit(CountryDto countryDto) {
        rabbitMessageSenderService.sendMessageAddCountry(countryDto);
    }

    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        return null;
    }

    public void deleteCountry(Long id) {
        rabbitMessageSenderService.sendMessageDeleteCountry(id);
    }
}
