package com.polovnev.country.controller;


import com.polovnev.country.dto.CountryDto;
import com.polovnev.country.facade.CountryFacade;
import com.polovnev.country.service.RabbitMessageSenderService;
import com.polovnev.country.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryFacade countryFacade;

    @Autowired
    private RabbitMessageSenderService customMessageSenderService;

    @GetMapping
    public ResponseEntity<String> getAllCountries() throws URISyntaxException {
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
