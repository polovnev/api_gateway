package com.polovnev.country.service.impl;

import com.polovnev.country.dao.RoleRepository;
import com.polovnev.country.dao.UserRepository;
import com.polovnev.country.entity.Role;
import com.polovnev.country.entity.UserEntity;
import com.polovnev.country.service.UserService;
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
