package com.polovnev.api_gateway.service;


import java.net.URISyntaxException;


public interface RestMessageSenderService {

    <T> T sendGetRequest(String uriString, Class<T> clazz) throws URISyntaxException;

    <T> T sendPostRequest(String uriString, Object object, Class<T> clazz) throws URISyntaxException;

    void sendPutRequest(String uriString, Object object) throws URISyntaxException;
}
