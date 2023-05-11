package com.service.review;


import com.config.AwsConfig;
import com.config.MapperConfig;
import com.dto.responseDto.review.ReviewDeliveryResponseDto;
import com.dto.responseDto.review.ReviewImgResponseDto;
import com.dto.responseDto.review.ReviewMenuResponseDto;
import com.dto.responseDto.review.ReviewResponseDto;
import com.entity.review.Review;
import com.entity.review.ReviewImage;
import com.entity.review.ReviewMenu;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.review.ReviewRepository;
import com.repositoryImpl.review.ReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewSortService {

    private final MapperConfig mapperConfig;
    private final ReviewRepositoryImpl reviewRepositoryImpl;

    // 좋아요 많은 순으로
    public List<ReviewResponseDto> sortByLike(Long shopId) {
        List<Review> reviews = reviewRepositoryImpl.sortByLikeCount(shopId);
        return changeReviewDtoList(reviews);
    }

    // 최신순
    public List<ReviewResponseDto> sortByDateTime(Long shopId) {
        List<Review> reviews = reviewRepositoryImpl.sortByDateTime(shopId);
        return changeReviewDtoList(reviews);
    }

    public List<ReviewResponseDto> changeReviewDtoList(List<Review> reviews) {
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();
        for (Review element : reviews) {
            reviewResponseDtos.add(ReviewResponseDto.builder()
                    .content(element.getContent())
                    .reviewId(element.getId())
                    .shopId(element.getShopId())
                    .localDateTime(element.getLocalDateTime())
                    .likeCount(element.getLikeCount())
                    .starPoint(element.getStarPoint())
                    .reviewDeliveryResponseDto(mapperConfig.modelMapper().map(element.getReviewDelivery(), ReviewDeliveryResponseDto.class))
                    .reviewMenuResponseDtos(changeMenuResponseDto(element.getReviewMenus()))
                    .reviewImgResponseDtos(changeImgResponseDto(element.getReviewImages()))
                    .build());
        }
        return reviewResponseDtos;


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

}
