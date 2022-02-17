package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;


@Component
public class LocationFacade {

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Value("${service.url.location}")
    private String baseUrlLocation;


    public String findByCountryId(Long countryId) throws URISyntaxException {
        String uri = baseUrlLocation + "/country/" + countryId + "/location";
        return restMessageSenderService.sendGetRequest(uri, String.class);
    }
}
