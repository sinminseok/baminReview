package com.service.review;


import com.dto.requestDto.ReviewImgRequestDto;
import com.dto.requestDto.ReviewRequestDto;
import com.entity.review.Review;
import com.repository.review.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReviewRegisterServiceTest {

    @Autowired
    private ReviewRegisterService reviewRegisterService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    public void register등록테스트(){
        ReviewRequestDto reviewRequestDto = createReviewRequestDto();
        Long register = reviewRegisterService.register(reviewRequestDto);
        Optional<Review> byId = reviewRepository.findById(register);
        System.out.println(byId.get().getReviewImages().size());
        System.out.println("asdasdasd");

        Assertions.assertThat(register).isNotNull();

    }

    public List<ReviewImgRequestDto> createReviewImgRequestDto(){
        return Collections.singletonList(ReviewImgRequestDto.builder()
                .imageUrl("imageUURRLL")
                .build());
    }

    public ReviewRequestDto createReviewRequestDto(){
        return ReviewRequestDto.builder()
                .content("testcontent")
                .memberNumber(1L)
                .orderId(2L)
                .shopId(3L)
                .starPoint(3.5)
                .reviewImgRequestDtos(createReviewImgRequestDto())
                .build();
    }
}
