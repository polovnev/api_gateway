package com.polovnev.country.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {

    private final String jwt;
}
