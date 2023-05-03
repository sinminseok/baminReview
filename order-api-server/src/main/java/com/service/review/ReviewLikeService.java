package com.service.review;

import com.entity.review.Review;
import com.entity.review.ReviewLike;
import com.repository.review.ReviewLikeRepository;
import com.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    private final ReviewRepository reviewRepository;

    //리뷰 좋아요 증가
    public boolean increaseLike(Long reviewId, Long memberNumber) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        Optional<ReviewLike> reviewLike1 = reviewLikeRepository.searchByMemberNumber(memberNumber);
        if (reviewLike1.isEmpty()) {
            ReviewLike reviewLike = ReviewLike.builder()
                    .memberNumber(memberNumber)
                    .review(review)
                    .build();
            review.increaseLike(reviewLike);
            return true;
        } else {
            new Exception("이미 좋아요를 눌렀습니다.");
            return false;
        }

    }

    //리뷰 좋아요 감소
    public boolean decreaseLike(Long reviewId, Long memberNumber) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        Optional<ReviewLike> reviewLike = reviewLikeRepository.searchByMemberNumber(memberNumber);
        if (reviewLike.isEmpty()) {
            new Exception("해당 리뷰에 좋아요를 누르지 않았습니다.");
            return false;
        } else {
            review.decreaseLike(reviewLike.get());
            return true;
        }


    }

}
