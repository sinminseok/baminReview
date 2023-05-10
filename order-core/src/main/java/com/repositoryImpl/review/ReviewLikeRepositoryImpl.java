package com.repositoryImpl.review;


import static com.entity.review.QReviewLike.reviewLike;
import static com.entity.review.QReview.review;

import com.entity.review.ReviewLike;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewLikeRepositoryImpl {


    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    // 만약 memberNumber,reviewId 둘다 일치하지 않는 경우 ? 어케될깡~?
    public Optional<ReviewLike> searchByMemberNumber(Long memberNumber, Long reviewId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(reviewLike)
                .where(eqMemberNumber(memberNumber),
                        eqReview(reviewId)
                ).fetchOne());

    }

    private BooleanExpression eqMemberNumber(Long memberNumber) {
        return reviewLike.memberNumber.eq(memberNumber);
    }

    private BooleanExpression eqReview(Long reviewId) {
        return reviewLike.reviewId.eq(reviewId);
    }

}
