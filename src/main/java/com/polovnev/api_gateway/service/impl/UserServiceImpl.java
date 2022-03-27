package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.dao.UserRepository;
import com.polovnev.api_gateway.entity.CustomUserDetails;
import com.polovnev.api_gateway.entity.Role;
import com.polovnev.api_gateway.entity.UserEntity;
import com.polovnev.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
public class UserServiceImpl implements UserService {

    private static final Integer ID_ROLE_USER = 2;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registration(UserEntity user) {
        Role role = Role.builder().id(ID_ROLE_USER).build();
        user.setRole(role);
        user.setIsConfirmed(false);
        userRepository.save(user);
    }

    @Override
    public void activateUser(String username, String activationCode) {
        userRepository.activateUser(username, activationCode);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> user = userRepository.findUserByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
        return new CustomUserDetails(user.get());
    }

    @Override
    public Stream<UserEntity> getUsersByIds(Set<Long> ids) {
        Iterable<UserEntity> userEntityIterable = userRepository.findAllById(ids);
        return StreamSupport.stream(userEntityIterable.spliterator(), false);
    }

}
