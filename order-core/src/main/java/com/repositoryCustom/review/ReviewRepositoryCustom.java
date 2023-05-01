package com.repositoryCustom.review;

import com.entity.review.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findallByShopId(Long shopId);

    Review findByReviewId(Long reviewId);

}
