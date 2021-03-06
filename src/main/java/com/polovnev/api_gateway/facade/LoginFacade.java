package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.AuthenticationRequestDto;
import com.polovnev.api_gateway.dto.AuthenticationResponseDto;
import com.polovnev.api_gateway.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class LoginFacade {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationResponseDto createAuthenticationToken(AuthenticationRequestDto authenticationRequestDto) {
        final String username = authenticationRequestDto.getUsername();
        final String password = authenticationRequestDto.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponseDto(jwt, username);
    }
}
