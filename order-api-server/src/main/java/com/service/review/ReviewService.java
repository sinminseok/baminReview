package com.service.review;


import com.config.MapperConfig;
import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.dto.requestDto.review.ReviewImgRequestDto;
import com.dto.requestDto.review.ReviewMenuRequestDto;
import com.dto.requestDto.review.ReviewRequestDto;
import com.entity.review.Review;
import com.entity.review.ReviewDelivery;
import com.entity.review.ReviewImage;
import com.entity.review.ReviewMenu;
import com.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    //shopId로 모든 리뷰조회

    @Transactional
    public Long register(ReviewRequestDto reviewRequestDto) {
        Review review = Review.builder()
                .memberNumber(reviewRequestDto.getMemberNumber())
                .content(reviewRequestDto.getContent())
                .orderId(reviewRequestDto.getOrderId())
                .starPoint(reviewRequestDto.getStarPoint())
                .shopId(reviewRequestDto.getShopId())
                .reviewImages(new ArrayList<>())
                .reviewMenus(new ArrayList<>())
                .build();
        createReviewImages(reviewRequestDto, review);
        createReviewMenus(reviewRequestDto, review);
        createReviewDelivery(reviewRequestDto.getReviewDeliveryRequestDto(), review);

        Review save = reviewRepository.save(review);
        return save.getId();
    }

    @Transactional
    public void update(ReviewRequestDto reviewRequestDto) {

        Review review = reviewRepository.findById(reviewRequestDto.getReviewId()).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        //변경감지를 이용한 엔티티 수정
        updateReviewMenus(reviewRequestDto, review);
        updateReviewImgs(reviewRequestDto, review);
        updateReviewDelivery(reviewRequestDto, review);

        //새로 변경된 정보를 담을 엔티티 생성
        Review updateReview = Review.builder()
                .content(reviewRequestDto.getContent())
                .starPoint(reviewRequestDto.getStarPoint())
                .build();


        review.updateReview(updateReview);


    }

    public void updateReviewMenus(ReviewRequestDto reviewRequestDto, Review review) {
        for (int i = 0; i < review.getReviewMenus().size(); i++) {
            review.getReviewMenus().get(i).update(reviewRequestDto.getReviewMenuRequestDtos().get(i).getMenuName());
        }
    }

    public void updateReviewDelivery(ReviewRequestDto reviewRequestDto, Review review) {
        review.getReviewDelivery().update(reviewRequestDto.getReviewDeliveryRequestDto().getReviewDeliveryStatus(), reviewRequestDto.getReviewDeliveryRequestDto().getHateReason());
    }


    public void updateReviewImgs(ReviewRequestDto reviewRequestDto, Review review) {
        for (int i = 0; i < review.getReviewImages().size(); i++) {
            review.getReviewImages().get(i).update(reviewRequestDto.getReviewImgRequestDtos().get(i).getImageUrl());
        }
    }


    public void createReviewMenus(ReviewRequestDto reviewRequestDto, Review review) {
        for (ReviewMenuRequestDto element : reviewRequestDto.getReviewMenuRequestDtos()) {
            ReviewMenu reviewMenu = ReviewMenu.builder()
                    .menuName(element.getMenuName())
                    .review(review)
                    .build();
            review.getReviewMenus().add(reviewMenu);
        }
    }

    public void createReviewImages(ReviewRequestDto reviewRequestDto, Review review) {
        for (ReviewImgRequestDto element : reviewRequestDto.getReviewImgRequestDtos()) {
            ReviewImage reviewImage = ReviewImage.builder()
                    .imageUrl(element.getImageUrl())
                    .review(review)
                    .build();
            review.getReviewImages().add(reviewImage);
        }
    }

    public void createReviewDelivery(ReviewDeliveryRequestDto reviewDeliveryRequestDto, Review review) {
        ReviewDelivery reviewDelivery = ReviewDelivery.builder()
                .hateReason(reviewDeliveryRequestDto.getHateReason())
                .review(review)
                .reviewDeliveryStatus(reviewDeliveryRequestDto.getReviewDeliveryStatus())
                .build();
        review.addReviewDelivery(reviewDelivery);

    }
}
