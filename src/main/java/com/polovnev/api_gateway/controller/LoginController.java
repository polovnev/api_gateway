package com.polovnev.api_gateway.controller;


import com.polovnev.api_gateway.dto.AuthenticationRequestDto;
import com.polovnev.api_gateway.dto.AuthenticationResponseDto;
import com.polovnev.api_gateway.facade.LoginFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginFacade loginFacade;

    @PostMapping("/login")
    private AuthenticationResponseDto createAuthenticationToken(
            @RequestBody AuthenticationRequestDto authenticationRequestDto)
            throws Exception {
        return loginFacade.createAuthenticationToken(authenticationRequestDto);
    }
}
