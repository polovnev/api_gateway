package com.polovnev.country.converter;

import com.polovnev.country.dto.UserDto;
import com.polovnev.country.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserEntity userDtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
