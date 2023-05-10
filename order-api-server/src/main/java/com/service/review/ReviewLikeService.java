package com.service.review;

import com.entity.review.Review;
import com.entity.review.ReviewLike;
import com.repository.review.ReviewLikeRepository;
import com.repository.review.ReviewRepository;
import com.repositoryImpl.review.ReviewLikeRepositoryImpl;
import com.repositoryImpl.review.ReviewRepositoryImpl;
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
    private final ReviewRepositoryImpl reviewRepositoryImpl;
    private final ReviewLikeRepositoryImpl reviewLikeRepositoryImpl;

    //리뷰 좋아요 등록
    public boolean increaseLike(Long reviewId, Long memberNumber) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        Optional<ReviewLike> reviewLike1 = reviewLikeRepositoryImpl.searchByMemberNumber(memberNumber,review.getId());
        if (reviewLike1.isEmpty()) {
            ReviewLike reviewLike = ReviewLike.builder()
                    .memberNumber(memberNumber)
                    .reviewId(review.getId())
                    .build();
            review.increaseLike();
            reviewLikeRepository.save(reviewLike);
            return true;
        } else {
            new Exception("이미 좋아요를 눌렀습니다.");
            return false;
        }

    }

    //리뷰 좋아요 취소
    public boolean decreaseLike(Long reviewId, Long memberNumber) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 리뷰입니다."));
        Optional<ReviewLike> reviewLike = reviewLikeRepositoryImpl.searchByMemberNumber(memberNumber,reviewId);
        if (reviewLike.isEmpty()) {
            new Exception("해당 리뷰에 좋아요를 누르지 않았습니다.");
            return false;
        } else {
            review.decreaseLike();
            reviewLikeRepository.delete(reviewLike.get());
            return true;
        }


    }

}
