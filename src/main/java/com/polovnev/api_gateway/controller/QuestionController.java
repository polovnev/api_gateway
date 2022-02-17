package com.polovnev.api_gateway.controller;


import com.polovnev.api_gateway.dto.QuestionDto;
import com.polovnev.api_gateway.dto.SearchRequest;
import com.polovnev.api_gateway.facade.QuestionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionFacade questionFacade;

    @PostMapping("/find")
    public List<QuestionDto> findQuestionByRequest(@RequestBody SearchRequest searchRequest) throws URISyntaxException {
        return questionFacade.findQuestionByRequest(searchRequest);
    }
}
