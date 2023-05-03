package com.repositoryCustom.review;

import com.entity.review.ReviewLike;

import java.util.Optional;

public interface ReviewLikeRepositoryCustom {
    Optional<ReviewLike> searchByMemberNumber(Long memberNumber);



}
