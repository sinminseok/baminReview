package com.repositoryImpl.review;

import com.entity.review.QReview;
import com.entity.review.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.entity.review.QReview.review;
import static com.entity.review.QReviewMenu.reviewMenu;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;


    //shop에 등록된 모든 review 조회
    public List<Review> findAllByShopId(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .where(checkShopId(shopId))
                .join(review.reviewMenus, reviewMenu).fetchJoin()
                .fetch();
    }

    //이미지가 없는리뷰
    public List<Review> findAllWithOutImg(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .where(checkShopId(shopId), existImage(review,false))
                .join(review.reviewMenus,reviewMenu).fetchJoin()
                .fetch();
    }

    //이미지가 있는 리뷰
    public List<Review> findAllWithImg(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .where(checkShopId(shopId), existImage(review,true))
                .join(review.reviewMenus,reviewMenu).fetchJoin()
                .fetch();
    }

    //reviewId로 단일 조회
    public Optional<Review> findByReviewId(Long reviewId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(review)
                .where(review.id.eq(reviewId))
                .join(review.reviewMenus, reviewMenu).fetchJoin()
                .fetchOne());
    }


    //최신순 정렬
    public List<Review> sortByDateTime(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .orderBy(
                        review.createdDate.desc()
                )
                .where(review.shopId.eq(shopId))
                .fetch();
    }


    public List<Review> sortByLikeCount(Long shopId) {
        return jpaQueryFactory.selectFrom(review)
                .orderBy(
                        review.likeCount.desc()
                )
                .where(review.shopId.eq(shopId))
                .fetch();
    }

    private BooleanExpression checkShopId(Long shopId) {
        return review.shopId.eq(shopId);
    }


    private BooleanExpression existImage(QReview review,boolean exisit) {
        if(exisit == true){
            //이미지가 있는 리뷰 반환
            return review.reviewImages.isNotEmpty();
        }else{
            //이미지가 없는 리뷰 반환
            return review.reviewImages.isEmpty();
        }
    }


}
