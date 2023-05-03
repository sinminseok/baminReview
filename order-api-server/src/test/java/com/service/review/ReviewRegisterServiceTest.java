package com.service.review;


import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.dto.requestDto.review.ReviewImgRequestDto;
import com.dto.requestDto.review.ReviewMenuRequestDto;
import com.dto.requestDto.review.ReviewRequestDto;
import com.dto.responseDto.review.ReviewResponseDto;
import com.entity.review.Review;
import com.entity.review.ReviewDeliveryStatus;
import com.repository.review.ReviewRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


//추후 Mock 으로 변환
@SpringBootTest
public class ReviewRegisterServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewLikeService reviewLikeService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    @Transactional
    @Rollback(value = false)
    public void register등록테스트() {
        ReviewRequestDto reviewRequestDto = createReviewRequestDto();
        Long register = reviewService.register(reviewRequestDto);
        Optional<Review> byId = reviewRepository.findById(register);
        Assertions.assertThat(register).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void increaseLike테스트() {
        ReviewRequestDto reviewRequestDto = createReviewRequestDto();
        Long register = reviewService.register(reviewRequestDto);
        boolean value = reviewLikeService.increaseLike(register, 33L);
        Assertions.assertThat(value).isTrue();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void decreaseLike테스트() {
        ReviewRequestDto reviewRequestDto = createReviewRequestDto();
        Long register = reviewService.register(reviewRequestDto);
        reviewLikeService.increaseLike(register, 33L);
        reviewLikeService.decreaseLike(register, 33L);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void update테스트() {
        ReviewRequestDto reviewRequestDto = createReviewRequestDto();
        Long register = reviewService.register(reviewRequestDto);
        ReviewRequestDto updateDto = updateReviewRequestDto();
        reviewService.update(updateDto);

    }


    @Test
    @Transactional
//    @Rollback(value = false)
    public void findallByShopId테스트() {
        ReviewRequestDto reviewRequestDto = createReviewRequestDto();
        Long register = reviewService.register(reviewRequestDto);
        List<ReviewResponseDto> reviews = reviewService.findallByShopId(3L);
        System.out.println("reviews = " + reviews.get(0));
    }

    public List<ReviewImgRequestDto> createReviewImgRequestDto() {
        return Collections.singletonList(ReviewImgRequestDto.builder()
                .imageUrl("imageUURRLL")
                .build());
    }

    public List<ReviewMenuRequestDto> createReviewMenuRequestDto() {
        return Collections.singletonList(ReviewMenuRequestDto.builder()
                .menuName("test menu name")
                .build());
    }

    public ReviewDeliveryRequestDto createReviewDeliveryRequestDto() {
        return ReviewDeliveryRequestDto.builder()
                .reviewDeliveryStatus(ReviewDeliveryStatus.LIKE)
                .hateReason("Because ~")
                .build();
    }

    public ReviewRequestDto createReviewRequestDto() {
        return ReviewRequestDto.builder()
                .content("testcontent")
                .memberNumber(1L)
                .orderId(2L)

                .shopId(3L)
                .starPoint(3.5)
                .reviewImgRequestDtos(createReviewImgRequestDto())
                .reviewDeliveryRequestDto(createReviewDeliveryRequestDto())
                .reviewMenuRequestDtos(createReviewMenuRequestDto())
                .build();
    }


    public List<ReviewImgRequestDto> updateReviewImgRequestDto() {
        return Collections.singletonList(ReviewImgRequestDto.builder()
                .imageUrl("update URL")
                .build());
    }

    public List<ReviewMenuRequestDto> updateReviewMenuRequestDto() {
        return Collections.singletonList(ReviewMenuRequestDto.builder()
                .menuName("update menu Name")
                .id(1L)
                .build());
    }

    public ReviewDeliveryRequestDto updateReviewDeliveryRequestDto() {
        return ReviewDeliveryRequestDto.builder()
                .reviewDeliveryStatus(ReviewDeliveryStatus.LIKE)
                .hateReason("update Reason ~")
                .build();
    }

    public ReviewRequestDto updateReviewRequestDto() {
        return ReviewRequestDto.builder()
                .content("updatecontent")
                .memberNumber(1L)
                .reviewId(1L)
                .orderId(2L)
                .shopId(3L)
                .starPoint(3.5)
                .reviewImgRequestDtos(updateReviewImgRequestDto())
                .reviewDeliveryRequestDto(updateReviewDeliveryRequestDto())
                .reviewMenuRequestDtos(updateReviewMenuRequestDto())
                .build();
    }
}
