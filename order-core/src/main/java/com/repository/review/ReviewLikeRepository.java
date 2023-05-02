package com.repository.review;

import com.entity.review.ReviewLike;
import com.repositoryCustom.review.ReviewLikeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike,Long> , ReviewLikeRepositoryCustom {

}
