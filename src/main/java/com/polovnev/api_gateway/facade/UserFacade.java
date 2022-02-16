package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.converter.UserConverter;
import com.polovnev.api_gateway.dto.UserDto;
import com.polovnev.api_gateway.entity.UserEntity;
import com.polovnev.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    public void registration(UserDto userDto) {
        UserEntity user = userConverter.userDtoToEntity(userDto);
        userService.registration(user);
    }
}
