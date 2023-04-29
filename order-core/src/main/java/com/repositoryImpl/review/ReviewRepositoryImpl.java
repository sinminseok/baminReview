package com.repositoryImpl.review;

import com.entity.review.QReviewImage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repositoryCustom.review.ReviewRepositoryCustom;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void registerImg(String imageUrl) {
        QReviewImage reviewImage = QReviewImage.reviewImage;
        jpaQueryFactory.insert(reviewImage);

    }
}
