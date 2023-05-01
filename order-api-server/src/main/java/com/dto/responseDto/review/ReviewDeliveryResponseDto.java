package com.dto.responseDto.review;


import com.entity.review.ReviewDeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDeliveryResponseDto {

    private ReviewDeliveryStatus reviewDeliveryStatus;

    private String hateReason;
}
