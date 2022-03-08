package com.polovnev.api_gateway.service;

import com.polovnev.api_gateway.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

public interface UserService extends UserDetailsService {

    void registration(UserEntity user);

    Stream<UserEntity> getUsersByIds(Set<Long> ids);

    void activateUser(String username, String activationCode);
}
