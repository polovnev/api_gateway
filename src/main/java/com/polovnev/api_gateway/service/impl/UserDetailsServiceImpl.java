package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.dao.UserRepository;
import com.polovnev.api_gateway.entity.CustomUserDetails;
import com.polovnev.api_gateway.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> user = userRepository.findUserByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));

        return new CustomUserDetails(user.get());
    }
}
