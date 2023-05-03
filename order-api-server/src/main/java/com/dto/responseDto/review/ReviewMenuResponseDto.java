package com.dto.responseDto.review;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMenuResponseDto {

    private Long id;
    private String menuName;
}
