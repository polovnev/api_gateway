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
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    public <T> List<T> setUsernameForDto(List<T> dtos,
                                               Function<T, Long> getAuthorId,
                                               BiConsumer<T, String> setUsername) {
        Set<Long> authorIds = dtos.stream().map(getAuthorId).collect(Collectors.toSet());
        Map<Long, String> mapIdUsername = getUsersByIds(authorIds)
                .collect(Collectors.toMap(UserEntity::getId, UserEntity::getUsername));
        dtos.forEach(dto -> {
            Long id = getAuthorId.apply(dto);
            String username = mapIdUsername.get(id);
            setUsername.accept(dto, username);
        });
        return dtos;
    }
}
