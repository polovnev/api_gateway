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
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


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

    public Stream<UserEntity> getUsersByIds(Set<Long> ids) {
        Iterable<UserEntity> userEntityIterable = userRepository.findAllById(ids);
        return StreamSupport.stream(userEntityIterable.spliterator(), false);
    }
}
