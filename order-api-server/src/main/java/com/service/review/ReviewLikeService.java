package com.service.review;

import com.entity.review.Review;
import com.entity.review.ReviewLike;
import com.repository.review.ReviewLikeRepository;
import com.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;

    public void increaseLike(Long reviewId,Long memberNumber){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        ReviewLike reviewLike = ReviewLike.builder()
                .memberNumber(memberNumber)
                .review(review)
                .build();
        review.increaseLike(reviewLike);
    }

    public void decreaseLike(Long reviewId,Long memberNumber){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        ReviewLike reviewLike = reviewLikeRepository.searchByMemberNumber(memberNumber);
        review.decreaseLike(reviewLike);

    }

}
