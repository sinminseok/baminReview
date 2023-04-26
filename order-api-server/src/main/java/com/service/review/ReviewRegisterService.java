package com.service.review;


import com.config.MapperConfig;
import com.dto.requestDto.ReviewImgRequestDto;
import com.dto.requestDto.ReviewRequestDto;
import com.entity.review.Review;
import com.entity.review.ReviewImage;
import com.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewRegisterService {

    private final MapperConfig mapperConfig;
    private final ReviewRepository reviewRepository;

    public Long register(ReviewRequestDto reviewRequestDto) {
        Review review = Review.builder()
                .memberNumber(reviewRequestDto.getMemberNumber())
                .content(reviewRequestDto.getContent())
                .orderId(reviewRequestDto.getOrderId())
                .starPoint(reviewRequestDto.getStarPoint())
                .shopId(reviewRequestDto.getShopId())
           //     .reviewImages(registerReviewImg(reviewRequestDto))
                .build();
        Review save = reviewRepository.save(review);
        return save.getId();
    }

    private List<ReviewImage> registerReviewImg(ReviewRequestDto reviewRequestDto) {
        List<ReviewImgRequestDto> reviewImgRequestDtos = reviewRequestDto.getReviewImgRequestDtos();
        List<ReviewImage> reviewImages = new ArrayList<>();
        for (ReviewImgRequestDto element : reviewImgRequestDtos) {
            reviewImages.add(mapperConfig.modelMapper().map(element, ReviewImage.class));
        }
        return reviewImages;

    }
}
