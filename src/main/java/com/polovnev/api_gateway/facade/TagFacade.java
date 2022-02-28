package com.polovnev.api_gateway.facade;


import com.polovnev.api_gateway.dto.TagDto;
import com.polovnev.api_gateway.service.RestMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Component
public class TagFacade {

    @Value("${service.url.question_response}")
    private String baseUrlQuestionResponse;

    @Autowired
    private RestMessageSenderService restMessageSenderService;

    public List<TagDto> getAllTags() throws URISyntaxException {
        String uri = baseUrlQuestionResponse + "/tag";
        TagDto[] tagDtos = restMessageSenderService.sendGetRequest(uri, TagDto[].class);
        return Arrays.asList(tagDtos);

    }
}
