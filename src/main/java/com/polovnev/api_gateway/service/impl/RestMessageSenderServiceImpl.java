package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RestMessageSenderServiceImpl implements RestMessageSenderService {

    @Value("${service.url.country}")
    private String baseUrlCountry;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> findAllCountries() throws URISyntaxException {
        URI uri = new URI(baseUrlCountry + "/country");
        return restTemplate.getForEntity(uri, String.class);
    }

    @Override
    public ResponseEntity<String> findByCountryId(Long countryId) throws URISyntaxException {
        URI uri = new URI(baseUrlCountry + "/country/" + countryId + "/location");
        return restTemplate.getForEntity(uri, String.class);
    }
}
