package com.polovnev.api_gateway.facade;

import com.polovnev.api_gateway.dto.QuestionDto;
import com.polovnev.api_gateway.dto.SearchRequest;
import com.polovnev.api_gateway.entity.UserEntity;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import com.polovnev.api_gateway.service.impl.UserDetailsServiceImpl;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class QuestionFacade {

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${service.url.question_response}")
    private String baseUrlQuestionResponse;

    public List<QuestionDto> findQuestionByRequest(SearchRequest searchRequest) throws URISyntaxException {

        String uri = baseUrlQuestionResponse + "/question/find";
        List<QuestionDto> questionDtos = Arrays.asList(restMessageSenderService.sendPostRequest(uri,
                searchRequest, QuestionDto[].class));
        return setUsernameForQuestionDto(questionDtos);
    }

    private List<QuestionDto> setUsernameForQuestionDto(List<QuestionDto> questionDtos) {
        Set<Long> authorIds = questionDtos.stream().map(QuestionDto::getAuthor).collect(Collectors.toSet());
        Map<Long, String> mapIdUsername = userDetailsService.getUsersByIds(authorIds)
                .collect(Collectors.toMap(UserEntity::getId, UserEntity::getUsername));
        questionDtos.forEach(questionDto -> {
            Long id = questionDto.getAuthor();
            String username = mapIdUsername.get(id);
            questionDto.setAuthorName(username);
        });
        return questionDtos;
    }

}
