package com.service.review;
import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.dto.requestDto.review.ReviewImgRequestDto;
import com.dto.requestDto.review.ReviewMenuRequestDto;
import com.dto.requestDto.review.ReviewRequestDto;
import com.dto.responseDto.review.ReviewResponseDto;
import com.entity.review.*;
import com.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;


    //shop에 등록된 모든 review 조회
    public List<Review> findallByShopId(Long shopId){
        List<Review> reviews = reviewRepository.searchAllByShopId(shopId);

        return reviews;
    }

    //review 개별조회
    public Review findById(Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        return review;
    }

    //review 등록
    public Long register(ReviewRequestDto reviewRequestDto) {
        Review review = Review.builder()
                .memberNumber(reviewRequestDto.getMemberNumber())
                .content(reviewRequestDto.getContent())
                .orderId(reviewRequestDto.getOrderId())
                .starPoint(reviewRequestDto.getStarPoint())
                .likeCount(0L)
                .shopId(reviewRequestDto.getShopId())
                .reviewLikes(new ArrayList<>())
                .reviewImages(new ArrayList<>())
                .reviewMenus(new ArrayList<>())
                .build();
        createReviewImages(reviewRequestDto, review);
        createReviewMenus(reviewRequestDto, review);
        createReviewDelivery(reviewRequestDto.getReviewDeliveryRequestDto(), review);

        Review save = reviewRepository.save(review);
        return save.getId();
    }

    //review 업데이트
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
