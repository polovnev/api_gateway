package com.polovnev.api_gateway.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {

    private String text;
    private List<Long> tags;
    private Long locationId;
}
