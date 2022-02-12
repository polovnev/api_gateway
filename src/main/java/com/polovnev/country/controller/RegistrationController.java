package com.polovnev.country.controller;

import com.polovnev.country.dto.UserDto;
import com.polovnev.country.facade.UserFacade;
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
