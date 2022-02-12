package com.polovnev.country.facade;

import com.polovnev.country.dto.UserDto;
import com.polovnev.country.entity.UserEntity;
import com.polovnev.country.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public void registration(UserDto userDto) {
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        userService.registration(user);
    }
}
