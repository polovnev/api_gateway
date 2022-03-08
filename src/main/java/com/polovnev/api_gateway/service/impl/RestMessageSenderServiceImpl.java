package com.polovnev.api_gateway.service.impl;


import com.polovnev.api_gateway.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RestMessageSenderServiceImpl implements RestMessageSenderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T sendGetRequest(String uriString, Class<T> clazz) throws URISyntaxException {
        URI uri = new URI(uriString);
        return restTemplate.getForEntity(uri, clazz).getBody();
    }

    @Override
    public <T> T sendPostRequest(String uriString, Object object, Class<T> clazz) throws URISyntaxException {
        URI uri = new URI(uriString);
        return restTemplate.postForEntity(uri, object, clazz).getBody();
    }

    @Override
    public void sendPutRequest(String uriString, Object object) throws URISyntaxException {
        URI uri = new URI(uriString);
        restTemplate.put(uri, object);
    }
}
