package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.CountryDto;
import com.polovnev.api_gateway.service.RabbitMessageSenderService;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class CountryFacade {

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Autowired
    private RabbitMessageSenderService rabbitMessageSenderService;

    @Value("${service.url.location}")
    private String baseUrlLocation;


    public String findAll() throws URISyntaxException {
        String uri = baseUrlLocation + "/country";
        return restMessageSenderService.sendGetRequest(uri, String.class);
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
