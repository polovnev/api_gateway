package com.polovnev.country.facade;

import com.polovnev.country.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;


@Component
public class LocationFacade {

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    public ResponseEntity<String> findByCountryId(Long countryId) throws URISyntaxException {
        return restMessageSenderService.findByCountryId(countryId);
    }
}
