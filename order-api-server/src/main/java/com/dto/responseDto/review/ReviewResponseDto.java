package com.dto.responseDto.review;


import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.dto.requestDto.review.ReviewImgRequestDto;
import com.dto.requestDto.review.ReviewMenuRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private Long reviewId;

    private Long memberNumber;

    private Long orderId;

    private Long shopId;

    private Long likeCount;

    private LocalDateTime localDateTime;

    private String content;

    private double starPoint;

    private List<ReviewImgResponseDto> reviewImgResponseDtos;

    private List<ReviewMenuResponseDto> reviewMenuResponseDtos;

    private ReviewDeliveryResponseDto reviewDeliveryResponseDto;
}
