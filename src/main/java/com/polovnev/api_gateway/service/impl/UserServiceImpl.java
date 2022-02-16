package com.polovnev.api_gateway.service.impl;

import com.polovnev.api_gateway.dao.UserRepository;
import com.polovnev.api_gateway.entity.Role;
import com.polovnev.api_gateway.entity.UserEntity;
import com.polovnev.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
