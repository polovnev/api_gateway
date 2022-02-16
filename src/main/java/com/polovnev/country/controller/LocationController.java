package com.polovnev.country.controller;

import com.polovnev.country.facade.LocationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URISyntaxException;


public class LocationController {

    @Autowired
    private LocationFacade locationFacade;

    @GetMapping
    public ResponseEntity<String> findByCountryId(@PathVariable Long countryId) throws URISyntaxException {
        return locationFacade.findByCountryId(countryId);
    }
}
