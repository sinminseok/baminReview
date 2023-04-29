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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
//Review는 루트애그리거트이다. 따라서 Review를 통해서만 CRUD가 진행되고 완전한 객체를 제공하고 관리해야합니다.
public class ReviewRegisterService {

    //같은 라이프사이클을 갖는 엔티티를 각각의 repository로 관리하면 외부에서 엔티티에 직접 접근해 데이터를 변경할 수 있게되어버리고 일관성이 깨짐
    private final ReviewRepository reviewRepository;

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
