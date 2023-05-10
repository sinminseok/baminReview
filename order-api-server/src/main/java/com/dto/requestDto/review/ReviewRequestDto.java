package com.dto.requestDto.review;


import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {


    private Long reviewId;

    private Long memberNumber;

    private Long orderId;

    private Long shopId;

    private String content;



    private double starPoint;

    private List<ReviewImgRequestDto> reviewImgRequestDtos;

    private List<ReviewMenuRequestDto> reviewMenuRequestDtos;

    private ReviewDeliveryRequestDto reviewDeliveryRequestDto;


}
