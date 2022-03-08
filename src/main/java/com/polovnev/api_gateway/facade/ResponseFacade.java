package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.QuestionDto;
import com.polovnev.api_gateway.dto.ResponseDto;
import com.polovnev.api_gateway.service.RabbitMessageSenderService;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import com.polovnev.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ResponseFacade {

    @Value("${service.url.question_response}")
    private String baseUrlQuestionResponse;

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Autowired
    private RabbitMessageSenderService rabbitMessageSenderService;

    @Autowired
    private UserService userService;

    public List<ResponseDto> findResponsesByQuestionId(Long questionId) throws URISyntaxException {
        String uri = baseUrlQuestionResponse + "/question/" + questionId + "/response";
        ResponseDto[] responseDtos = restMessageSenderService.sendGetRequest(uri, ResponseDto[].class);
        return Arrays.asList(responseDtos);
    }

    public void createResponse(Long questionId, ResponseDto responseDto) {
        responseDto.setQuestion(QuestionDto.builder().id(questionId).build());
        rabbitMessageSenderService.sendMessageCreateResponse(responseDto);
    }

    public void setIsResponseTrue(Long responseId, Long questionId, String userId) throws URISyntaxException {
        String uri = baseUrlQuestionResponse + "/question/" + null + "/response/" + responseId;
        restMessageSenderService.sendPutRequest(uri, Optional.empty());
    }

}
