package com.repositoryImpl.review;

import com.entity.review.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repositoryCustom.review.ReviewRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QReview review = QReview.review;
    private QReviewImage reviewImage = QReviewImage.reviewImage;
    private QReviewMenu reviewMenu = QReviewMenu.reviewMenu;
    private QReviewDelivery qReviewDelivery = QReviewDelivery.reviewDelivery;

    //.fetch() 는 리스트로 결과를 반환하는 방법이고 데이터가 없을경우 빈 리스트를 반환해준다.
    @Override
    public List<Review> findallByShopId(Long shopId) {
        return jpaQueryFactory.selectFrom(review).where(review.shopId.eq(shopId)).fetch();
    }

    @Override
    public Review findByReviewId(Long reviewId) {
        return jpaQueryFactory.selectFrom(review)
                .innerJoin(review.reviewImages,reviewImage)
                .fetchJoin()
                .fetchOne();

    }
}
