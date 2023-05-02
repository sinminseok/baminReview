package com.repositoryCustom.review;

import com.entity.review.ReviewLike;

public interface ReviewLikeRepositoryCustom {
    ReviewLike searchByMemberNumber(Long memberNumber);
}
