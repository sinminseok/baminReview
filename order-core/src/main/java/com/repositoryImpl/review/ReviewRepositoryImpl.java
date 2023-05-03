package com.repositoryImpl.review;

import com.entity.review.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repositoryCustom.review.ReviewRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QReview review = QReview.review;

    //.fetch() 는 리스트로 결과를 반환하는 방법이고 데이터가 없을경우 빈 리스트를 반환해준다.
    @Override
    public List<Review> searchAllByShopId(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .join(review.reviewImages).fetchJoin()
                .where(review.shopId.eq(shopId)).fetch();
    }

    @Override
    public List<Review> searchArrangeLike(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .orderBy(
                        review.likeCount.desc()
                )
                .where(review.shopId.eq(shopId))
                .fetch();
    }

    @Override
    public List<Review> searchArrangeDatetime(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .orderBy(
                        review.createdBy.desc()
                )
                .where(review.shopId.eq(shopId))
                .fetch();
    }


//    @Override
//    public List<Academy> searchByDynamic(Long m, String phoneNumber) {
//        return queryFactory
//                .selectFrom(academy)
//                .where(eqName(name),
//                        eqAddress(address),
//                        eqPhoneNumber(phoneNumber))
//                .fetch();
//    }
//
//    //BooleanExpression은 where절에서 사용 할 수 있는값이다.
//    private BooleanExpression eqName(String name) {
//        if (StringUtils.isEmpty(name)) {
//            return null;
//        }
//        return academy.name.eq(name);
//    }
//
//    private BooleanExpression eqAddress(String address) {
//        if (StringUtils.isEmpty(address)) {
//            return null;
//        }
//        return academy.address.eq(address);
//    }







}
