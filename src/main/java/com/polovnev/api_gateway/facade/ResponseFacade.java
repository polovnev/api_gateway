package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.ResponseDto;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import com.polovnev.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Component
public class ResponseFacade {

    @Value("${service.url.question_response}")
    private String baseUrlQuestionResponse;

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Autowired
    private UserService userService;

    public List<ResponseDto> findResponsesByQuestionId(Long questionId) throws URISyntaxException {
        String uri = baseUrlQuestionResponse + "/question/" + questionId + "/response";
        ResponseDto[] responseDtos = restMessageSenderService.sendGetRequest(uri, ResponseDto[].class);
        List<ResponseDto> responseDtoList = Arrays.asList(responseDtos);
        return userService.setUsernameForDto(responseDtoList,
                ResponseDto::getAuthor, ResponseDto::setAuthorName);
    }

}
