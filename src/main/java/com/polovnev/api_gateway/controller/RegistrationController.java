package com.polovnev.api_gateway.controller;

import com.polovnev.api_gateway.dto.UserDto;
import com.polovnev.api_gateway.facade.RegistrationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationFacade registrationFacade;

    @PostMapping("/registration")
    public void registration(@RequestBody @Valid UserDto userDto){
        registrationFacade.registration(userDto);
    }

    @GetMapping("/activate")
    public void activateUser(@RequestParam String username, @RequestParam String activationCode) {
        registrationFacade.activateUser(username, activationCode);
    }

}
