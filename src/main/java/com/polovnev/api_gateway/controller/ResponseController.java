package com.polovnev.api_gateway.controller;

import com.polovnev.api_gateway.dto.ResponseDto;
import com.polovnev.api_gateway.facade.ResponseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/question/{questionId}/response")
public class ResponseController {

    @Autowired
    private ResponseFacade responseFacade;

    @PostMapping
    public void addResponse(@PathVariable Long questionId, @RequestBody ResponseDto responseDto) {
       // responseFacade.addResponse(questionId, responseDto);
    }

    @GetMapping
    public List<ResponseDto> findResponsesByQuestionId(@PathVariable Long questionId) throws URISyntaxException {
        return responseFacade.findResponsesByQuestionId(questionId);
    }
}
