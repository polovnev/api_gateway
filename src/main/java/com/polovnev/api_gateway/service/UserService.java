package com.polovnev.api_gateway.service;

import com.polovnev.api_gateway.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;
import java.util.stream.Stream;

public interface UserService extends UserDetailsService {

    void registration(UserEntity user);

    Stream<UserEntity> getUsersByIds(Set<Long> ids);
}
