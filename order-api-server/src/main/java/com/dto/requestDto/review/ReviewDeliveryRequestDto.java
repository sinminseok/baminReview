package com.dto.requestDto.review;

import com.entity.review.ReviewDeliveryStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDeliveryRequestDto {

    @Enumerated(EnumType.STRING)
    private ReviewDeliveryStatus reviewDeliveryStatus;

    private String hateReason;
}
