package com.polovnev.api_gateway.controller;


import com.polovnev.api_gateway.dto.AuthenticationRequestDto;
import com.polovnev.api_gateway.dto.AuthenticationResponseDto;
import com.polovnev.api_gateway.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    private AuthenticationResponseDto createAuthenticationToken(
            @RequestBody AuthenticationRequestDto authenticationRequestDto)
            throws Exception {
        final String username = authenticationRequestDto.getUsername();
        final String password = authenticationRequestDto.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponseDto(jwt);
    }
}
