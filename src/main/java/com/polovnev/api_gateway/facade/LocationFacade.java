package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.service.RestMessageSenderService;
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
