package com.polovnev.api_gateway.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private Long id;
    private String text;
    private Long authorId;
    private String authorName;
    private Boolean isResponse;
    private QuestionDto question;

}
