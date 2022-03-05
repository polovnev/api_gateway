package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.converter.UserConverter;
import com.polovnev.api_gateway.dto.UserDto;
import com.polovnev.api_gateway.entity.UserEntity;
import com.polovnev.api_gateway.service.NotificationService;
import com.polovnev.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Component
public class RegistrationFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private NotificationService notificationService;


    @Transactional
    public void registration(UserDto userDto) {
        UserEntity user = userConverter.userDtoToEntity(userDto);
        final String activationCode = generateActivationCode();
        user.setActivationCode(activationCode);
        userService.registration(user);
        notificationService.sendEmail(user.getEmail(), activationCode);
    }

    public void activateUser(String username, String activationCode) {
       userService.activateUser(username, activationCode);
    }

    private String generateActivationCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
