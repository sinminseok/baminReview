package com.dto.requestDto;


import com.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {

    private Long memberNumber;

    private Long orderId;

    private Long shopId;

    private String content;

    private double starPoint;

    private List<ReviewImgRequestDto> reviewImgRequestDtos;



}
