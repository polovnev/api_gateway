package com.polovnev.api_gateway.controller;


import com.polovnev.api_gateway.dto.CountryDto;
import com.polovnev.api_gateway.facade.CountryFacade;
import com.polovnev.api_gateway.service.RabbitMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryFacade countryFacade;

    @Autowired
    private RabbitMessageSenderService customMessageSenderService;

    @GetMapping
    public String getAllCountries() throws URISyntaxException {
        return countryFacade.findAll();
    }

    @GetMapping("/{id}")
    public CountryDto getCountry(@PathVariable Long id) {
        return countryFacade.findById(id);
    }

    @PostMapping
    public void addCountry(@RequestBody CountryDto countryDto) {
         countryFacade.addCountryRabbit(countryDto);
    }

    @PutMapping("/{id}")
    public CountryDto updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        return countryFacade.updateCountry(id, countryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
         countryFacade.deleteCountry(id);
    }


}
