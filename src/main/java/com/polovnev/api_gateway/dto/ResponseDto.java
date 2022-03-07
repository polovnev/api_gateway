package com.polovnev.api_gateway.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private Long responseId;
    private String text;
    private Long author;
    private String authorName;
    private Boolean isResponse;
    private QuestionDto question;

}
