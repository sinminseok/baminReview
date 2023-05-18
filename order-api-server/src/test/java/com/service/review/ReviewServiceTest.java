package com.service.review;


import com.config.AwsConfig;
import com.config.MapperConfig;
import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.dto.requestDto.review.ReviewImgRequestDto;
import com.dto.requestDto.review.ReviewMenuRequestDto;
import com.dto.requestDto.review.ReviewRequestDto;
import com.dto.responseDto.review.ReviewResponseDto;
import com.entity.review.Review;
import com.entity.review.ReviewDeliveryStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.review.ReviewRepository;
import com.repositoryImpl.review.ReviewRepositoryImpl;
import com.sns.ReviewSnsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.service.review.ReviewTestFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

//추후 Mock 으로 변환
@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ReviewRepositoryImpl reviewRepositoryImple;
    @Mock
    private ReviewSnsService reviewSnsService;

    @Spy
    private MapperConfig mapperConfig;
    @InjectMocks
    private ReviewService reviewService;

    private ReviewRequestDto reviewRequestDto;


    @BeforeEach
    void set(){
        reviewRequestDto = createReviewRequestDto("내용");
    }

    @Test
    @Rollback(value = false)
    @DisplayName("review등록 테스트")
    public void register등록() throws JsonProcessingException {
        //given

        given(reviewRepository.save(any())).willReturn(mockReview());
        //sns 관련 테스트는 무시
        given(reviewSnsService.createSns(any())).willReturn(null);

        //when
        Long register = reviewService.register(reviewRequestDto);

        //then
        Assertions.assertThat(register).isNotNull();
    }

    @Test
    @DisplayName("review단일조회 테스트")
    public void findById테스트() throws JsonProcessingException {

        //given
        given(reviewRepositoryImple.findByReviewId(any())).willReturn(Optional.ofNullable(mockReview()));

        //when
        ReviewResponseDto byId = reviewService.findById(1L);

        System.out.println(byId);
        //then

    }

    public Review mockReview(){
        return Review.builder()
                .id(1L)
                .content("mockContent")
                .build();
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

    public List<ReviewRequestDto> createReviewDtoList(){
        List<ReviewRequestDto> reviewRequestDtos = new ArrayList<>();
        for(int i=0;i<100;i++){
            reviewRequestDtos.add(createReviewRequestDto(String.valueOf(i)));
        }
        return reviewRequestDtos;
    }

    public ReviewRequestDto createReviewRequestDto(String content) {
        return ReviewRequestDto.builder()
                .content("content number = "+content)
                .memberNumber(REVIEW_ID)
                .orderId(ORDER_ID)
                .shopId(SHOP_ID)
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
