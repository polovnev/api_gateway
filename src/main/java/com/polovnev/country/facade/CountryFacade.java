package com.polovnev.country.facade;

import com.polovnev.country.dto.CountryDto;
import com.polovnev.country.service.CustomMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryFacade {


    @Autowired
    private CustomMessageSenderService customMessageSenderService;


    public List<CountryDto> findAll() {
        return null;
    }

    public CountryDto findById(Long id) {
        return null;
    }

    public CountryDto addCountry(CountryDto countryDto) {
        return null;
    }

    public void addCountryRabbit(CountryDto countryDto) {
        customMessageSenderService.sendMessage(countryDto);
    }

    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        return null;
    }

    public void deleteCountry(Long id) {
    }
}
