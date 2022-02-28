package com.polovnev.api_gateway.controller;


import com.polovnev.api_gateway.dto.TagDto;
import com.polovnev.api_gateway.facade.TagFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagFacade tagFacade;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagDto> getAllTags() throws URISyntaxException {
        return tagFacade.getAllTags();
    }

}
