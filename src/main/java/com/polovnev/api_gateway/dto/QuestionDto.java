package com.polovnev.api_gateway.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class QuestionDto {

    private Long id;
    private Long ratePoints;
    @NotBlank
    private String text;
    @NotNull
    private Long author;
    private String authorName;
    @NotNull
    private Long location;
    private Boolean isResponded;
    private LocalDate createdDate;
    private List<TagDto> tags;
    private List<ResponseDto> responses;

}
