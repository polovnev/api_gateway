package com.polovnev.api_gateway.controller;

import com.polovnev.api_gateway.facade.LocationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/country/{countryId}/location")
public class LocationController {

    @Autowired
    private LocationFacade locationFacade;

    @GetMapping
    public String findByCountryId(@PathVariable Long countryId) throws URISyntaxException {
        return locationFacade.findByCountryId(countryId);
    }
}
