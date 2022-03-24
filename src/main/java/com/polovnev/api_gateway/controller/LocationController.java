package com.polovnev.api_gateway.controller;

import com.polovnev.api_gateway.dto.LocationDto;
import com.polovnev.api_gateway.facade.LocationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{locationId}")
    public LocationDto findByLocationId(@PathVariable Long locationId) throws URISyntaxException {
        return locationFacade.getLocationById(locationId);
    }
}
