package com.service.review;

import com.config.AwsConfig;
import com.config.MapperConfig;
import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.dto.requestDto.review.ReviewImgRequestDto;
import com.dto.requestDto.review.ReviewMenuRequestDto;
import com.dto.requestDto.review.ReviewRequestDto;
import com.dto.responseDto.review.ReviewDeliveryResponseDto;
import com.dto.responseDto.review.ReviewImgResponseDto;
import com.dto.responseDto.review.ReviewMenuResponseDto;
import com.dto.responseDto.review.ReviewResponseDto;
import com.entity.review.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.review.ReviewRepository;
import com.repositoryImpl.review.ReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MapperConfig mapperConfig;
    private final AwsConfig awsConfig;
    private final ObjectMapper objectMapper;
    private final ReviewRepositoryImpl reviewRepositoryImpl;

    //shop에 등록된 모든 review 조회
    public List<ReviewResponseDto> findallByShopId(Long shopId) {
        List<Review> reviews = reviewRepositoryImpl.findAllByShopId(shopId);
        return changeReviewDtoList(reviews);
    }


    //review 개별조회
    public ReviewResponseDto findById(Long reviewId) {
        Review review = reviewRepositoryImpl.findByReviewId(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        return changeReviewDto(review);
    }

    //review 등록
    public Long register(ReviewRequestDto reviewRequestDto) throws JsonProcessingException {
        Review review = Review.builder()
                .memberNumber(reviewRequestDto.getMemberNumber())
                .content(reviewRequestDto.getContent())
                .orderId(reviewRequestDto.getOrderId())
                .starPoint(reviewRequestDto.getStarPoint())
                .likeCount(0L)
                .shopId(reviewRequestDto.getShopId())
                .reviewImages(new ArrayList<>())
                .reviewMenus(new ArrayList<>())
                .build();
        createReviewImages(reviewRequestDto, review);
        createReviewMenus(reviewRequestDto, review);
        createReviewDelivery(reviewRequestDto.getReviewDeliveryRequestDto(), review);

        Review save = reviewRepository.save(review);
        //리뷰 이벤트 발송
    //    awsSnsPublishReview(review);

        return save.getId();
    }


    //review 수정
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




    // dto entity 변경
    private List<ReviewResponseDto> changeReviewDtoList(List<Review> reviews) {
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();
        for (Review element : reviews) {
            reviewResponseDtos.add(ReviewResponseDto.builder()
                    .content(element.getContent())
                    .reviewId(element.getId())
                    .shopId(element.getShopId())
                    .starPoint(element.getStarPoint())
                    .reviewDeliveryResponseDto(mapperConfig.modelMapper().map(element.getReviewDelivery(), ReviewDeliveryResponseDto.class))
                    .reviewMenuResponseDtos(changeMenuResponseDto(element.getReviewMenus()))
                    .reviewImgResponseDtos(changeImgResponseDto(element.getReviewImages()))
                    .build());
        }
        return reviewResponseDtos;


    }

    private ReviewResponseDto changeReviewDto(Review review){
        ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
                .content(review.getContent())
                .reviewId(review.getId())
                .shopId(review.getShopId())
                .starPoint(review.getStarPoint())
                .reviewDeliveryResponseDto(mapperConfig.modelMapper().map(review.getReviewDelivery(), ReviewDeliveryResponseDto.class))
                .reviewMenuResponseDtos(changeMenuResponseDto(review.getReviewMenus()))
                .reviewImgResponseDtos(changeImgResponseDto(review.getReviewImages()))

                .build();
        return reviewResponseDto;
    }

    private List<ReviewMenuResponseDto> changeMenuResponseDto(List<ReviewMenu> reviewMenus) {
        List<ReviewMenuResponseDto> reviewMenuResponseDtos = new ArrayList<>();
        for (ReviewMenu element : reviewMenus) {
            ReviewMenuResponseDto reviewMenu = ReviewMenuResponseDto.builder()
                    .id(element.getId())
                    .menuName(element.getMenuName())
                    .build();
            reviewMenuResponseDtos.add(reviewMenu);
        }
        return reviewMenuResponseDtos;
    }


    private List<ReviewImgResponseDto> changeImgResponseDto(List<ReviewImage> reviewImages) {
        List<ReviewImgResponseDto> reviewImgResponseDtos = new ArrayList<>();
        for (ReviewImage element : reviewImages) {
            ReviewImgResponseDto build = ReviewImgResponseDto.builder()
                    .id(element.getId())
                    .imageUrl(element.getImageUrl())
                    .build();
            reviewImgResponseDtos.add(build);
        }
        return reviewImgResponseDtos;

    }


    private void createReviewMenus(ReviewRequestDto reviewRequestDto, Review review) {
        for (ReviewMenuRequestDto element : reviewRequestDto.getReviewMenuRequestDtos()) {
            ReviewMenu reviewMenu = ReviewMenu.builder()
                    .menuName(element.getMenuName())
                    .review(review)
                    .build();
            review.getReviewMenus().add(reviewMenu);
        }
    }

    private void createReviewImages(ReviewRequestDto reviewRequestDto, Review review) {
        for (ReviewImgRequestDto element : reviewRequestDto.getReviewImgRequestDtos()) {
            ReviewImage reviewImage = ReviewImage.builder()
                    .imageUrl(element.getImageUrl())
                    .review(review)
                    .build();
            review.getReviewImages().add(reviewImage);
        }
    }

    private void createReviewDelivery(ReviewDeliveryRequestDto reviewDeliveryRequestDto, Review review) {
        ReviewDelivery reviewDelivery = ReviewDelivery.builder()
                .hateReason(reviewDeliveryRequestDto.getHateReason())
                .review(review)
                .reviewDeliveryStatus(reviewDeliveryRequestDto.getReviewDeliveryStatus())
                .build();
        review.addReviewDelivery(reviewDelivery);

    }


    private PublishResponse awsSnsPublishReview(Review scriptData) throws JsonProcessingException {
        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(awsConfig.getSnsTopicARN())
                .subject("Review Event!")
                .message(objectMapper.writeValueAsString(scriptData))
                .messageGroupId("sns-group")
                .build();


        SnsClient snsClient = awsConfig.getSnsClient();
        PublishResponse publishResponse = snsClient.publish(publishRequest);

        snsClient.close();
        return publishResponse;
    }
}
