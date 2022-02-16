package com.polovnev.country.service;


import org.springframework.http.ResponseEntity;
import java.net.URISyntaxException;


public interface RestMessageSenderService {

    ResponseEntity<String> findAllCountries() throws URISyntaxException;

    ResponseEntity<String> findByCountryId(Long countryId) throws URISyntaxException;

}
