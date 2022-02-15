package com.polovnev.country.facade;

import com.polovnev.country.converter.UserConverter;
import com.polovnev.country.dto.UserDto;
import com.polovnev.country.entity.UserEntity;
import com.polovnev.country.service.UserService;
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
