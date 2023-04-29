package com.service.review;


import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.dto.requestDto.review.ReviewImgRequestDto;
import com.dto.requestDto.review.ReviewMenuRequestDto;
import com.dto.requestDto.review.ReviewRequestDto;
import com.entity.review.Review;
import com.entity.review.ReviewDeliveryStatus;
import com.repository.review.ReviewRepository;
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
    private ReviewRegisterService reviewRegisterService;

    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    @Transactional
    @Rollback(value = false)
    public void register등록테스트() {
        ReviewRequestDto reviewRequestDto = createReviewRequestDto();
        Long register = reviewRegisterService.register(reviewRequestDto);
        Optional<Review> byId = reviewRepository.findById(register);
        Assertions.assertThat(register).isNotNull();

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 테스트(){
        Optional<Review> byId = reviewRepository.findById(2L);
        System.out.println(byId.get().getReviewImages());
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
}
