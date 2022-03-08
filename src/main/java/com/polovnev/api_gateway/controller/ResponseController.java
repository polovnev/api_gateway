package com.polovnev.api_gateway.controller;

import com.polovnev.api_gateway.dto.ResponseDto;
import com.polovnev.api_gateway.facade.ResponseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/question/{questionId}/response")
public class ResponseController {

    @Autowired
    private ResponseFacade responseFacade;

    @PostMapping
    public void createResponse(@PathVariable Long questionId, @RequestBody ResponseDto responseDto) {
        responseFacade.createResponse(questionId, responseDto);
    }

    @GetMapping
    public List<ResponseDto> findResponsesByQuestionId(@PathVariable Long questionId) throws URISyntaxException {
        return responseFacade.findResponsesByQuestionId(questionId);
    }

    @PutMapping("/{responseId}")
    public void setIsResponseTrue(@PathVariable(name = "responseId") Long responseId,
                                  @PathVariable(name = "questionId") Long questionId,
                                  Principal principal) throws URISyntaxException {
        responseFacade.setIsResponseTrue(responseId, questionId, principal.getName());
    }
}
