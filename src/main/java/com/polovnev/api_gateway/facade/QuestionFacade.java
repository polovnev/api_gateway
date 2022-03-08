package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.QuestionDto;
import com.polovnev.api_gateway.dto.ResponseDto;
import com.polovnev.api_gateway.dto.SearchRequest;
import com.polovnev.api_gateway.service.RabbitMessageSenderService;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import com.polovnev.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.*;

@Component
public class QuestionFacade {

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Autowired
    private RabbitMessageSenderService rabbitMessageSenderService;

    @Autowired
    private UserService userService;

    @Value("${service.url.question_response}")
    private String baseUrlQuestionResponse;

    public List<QuestionDto> findQuestionByRequest(SearchRequest searchRequest) throws URISyntaxException {
        String uri = baseUrlQuestionResponse + "/question/find";
        List<QuestionDto> questionDtos = Arrays.asList(restMessageSenderService.sendPostRequest(uri,
                searchRequest, QuestionDto[].class));
        return userService.setUsernameForDto(questionDtos, QuestionDto::getAuthorId, QuestionDto::setAuthorName);
    }

    public QuestionDto getQuestionById(Long id) throws URISyntaxException {
        String uri = baseUrlQuestionResponse + "/question/" + id;
        QuestionDto questionDto = restMessageSenderService.sendGetRequest(uri, QuestionDto.class);
        userService.setUsernameForDto(questionDto.getResponses(),
                ResponseDto::getAuthor, ResponseDto::setAuthorName);
        return userService
                .setUsernameForDto(Collections.singletonList(questionDto),
                        QuestionDto::getAuthorId, QuestionDto::setAuthorName).get(0);
    }

    public void createQuestion(QuestionDto questionDto) {
        rabbitMessageSenderService.sendMessageCreateQuestion(questionDto);
    }

}
