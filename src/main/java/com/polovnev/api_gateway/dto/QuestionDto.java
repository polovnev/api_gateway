package com.polovnev.api_gateway.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class QuestionDto {

    private Long id;
    private Long ratePoints;
    private String text;
    private Long author;
    private String authorName;
    private Long location;
    private Boolean isResponded;
    private LocalDate createdDate;
    private List<TagDto> tags;
    private List<ResponseDto> responses;

}
