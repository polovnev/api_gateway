package com.polovnev.api_gateway.service;

import com.polovnev.api_gateway.entity.UserEntity;

import java.util.Set;
import java.util.stream.Stream;

public interface UserService {

    void registration(UserEntity user);

    Stream<UserEntity> getUsersByIds(Set<Long> ids);
}
