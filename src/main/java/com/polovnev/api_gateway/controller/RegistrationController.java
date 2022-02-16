package com.polovnev.api_gateway.controller;

import com.polovnev.api_gateway.dto.UserDto;
import com.polovnev.api_gateway.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserFacade registrationFacade;

    @PostMapping("/registration")
    public void registration(@RequestBody UserDto userDto){
        registrationFacade.registration(userDto);
    }
}
