package com.repositoryCustom.review;

import com.entity.review.Review;
import com.entity.review.ReviewLike;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> searchAllByShopId(Long shopId);

//    List<Review> searchArrangeLike(Long shopId);

/*    List<Review> searchArrangeDatetime(Long shopId);*/


}
