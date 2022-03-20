package com.polovnev.api_gateway.controller;


import com.polovnev.api_gateway.dto.QuestionDto;
import com.polovnev.api_gateway.dto.SearchRequest;
import com.polovnev.api_gateway.facade.QuestionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionFacade questionFacade;

    @CrossOrigin
    @PostMapping("/find")
    public List<QuestionDto> findQuestionByRequest(@RequestBody SearchRequest searchRequest) throws URISyntaxException {
        return questionFacade.findQuestionByRequest(searchRequest);
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestionById(@PathVariable Long id) throws URISyntaxException {
        return questionFacade.getQuestionById(id);
    }

    @PostMapping
    public void createQuestion(@RequestBody @Valid QuestionDto questionDto) {
        questionFacade.createQuestion(questionDto);
    }
}
