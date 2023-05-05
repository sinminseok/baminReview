//package com.repositoryImpl.review;
//
//import com.entity.review.QReviewLike;
//import com.entity.review.ReviewLike;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.repositoryCustom.review.ReviewLikeRepositoryCustom;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class ReviewLikeRepositoryImpl implements ReviewLikeRepositoryCustom {
//
//    private final JPAQueryFactory jpaQueryFactory;
//    private QReviewLike reviewLike = QReviewLike.reviewLike;
//
//    @Override
//    public Optional<ReviewLike> searchByMemberNumber(Long memberNumber) {
//        return Optional.ofNullable(jpaQueryFactory.selectFrom(reviewLike)
//                .where(reviewLike.memberNumber.eq(memberNumber)).fetchOne());
//    }
//
//
//}
