package com.polovnev.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String start() {
        return "Hello, I am OK!";
    }

    @GetMapping("/hello")
    public String getHello() {
        HttpHeaders httpHeaders = new HttpHeaders();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8000/country/hello",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);

        return responseEntity.getBody();
    }
}
