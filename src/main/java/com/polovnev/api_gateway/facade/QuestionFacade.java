package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.QuestionDto;
import com.polovnev.api_gateway.dto.SearchRequest;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.List;

@Component
public class QuestionFacade {

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Value("${service.url.question_response}")
    private String baseUrlQuestionResponse;

    public List<QuestionDto> findQuestionByRequest(SearchRequest searchRequest) throws URISyntaxException {
        Class<List<QuestionDto>> typeOfListOfQuestionDto = new TypeToken<List<QuestionDto>>() {
        }.getRawType();
        String uri = baseUrlQuestionResponse + "/question/find";
        return restMessageSenderService.sendPostRequest(uri,
                searchRequest, typeOfListOfQuestionDto);
    }
}
