//package com.repository.review;
//
//import com.entity.review.*;
//import com.repositoryImpl.review.ReviewRepositoryImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.repository.review.ReviewFixture.*;
//
//@SpringBootTest
//public class ReviewRepositoryTest {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    private ReviewRepositoryImpl reviewRepositoryImpl;
//
//    private Review review;
//
////    @BeforeEach
////    public void set(){
////
////    }
//
//    private Review createReview(){
//        Review review = Review.builder()
//                .memberNumber(123L)
//                .content("review 내용")
//                .orderId(ORDER_ID)
//                .starPoint(3.5)
//                .likeCount(0L)
//                .shopId(SHOP_ID)
//                .reviewImages(new ArrayList<>())
//                .reviewMenus(new ArrayList<>())
//                .build();
//        createReviewDelivery(review);
//        createReviewMenus(review);
//        createReviewImgs(review);
//        return review;
//    }
//
//    private ReviewDelivery createReviewDelivery(Review review){
//        return ReviewDelivery.builder()
//                .reviewDeliveryStatus(ReviewDeliveryStatus.LIKE)
//                .hateReason("SOSO")
//                .review(review)
//                .build();
//    }
//
//    public void createReviewImgs(Review review){
//        for(int i=0;i<3;i++){
//            reviewImages.add(ReviewImage.builder()
//                            .imageUrl("imageURL"+i)
//                    .review(review)
//                    .build());
//            review.getReviewImages().add(re)
//        }
//        return reviewImages;
//    }
//
//    private List<ReviewMenu> createReviewMenus(Review review){
//        List<ReviewMenu> reviewImages = new ArrayList<>();
//        for(int i=0;i<3;i++){
//            reviewImages.add(ReviewMenu.builder()
//                            .menuName("menu이름"+i)
//                            .review(review)
//                    .build());
//        }
//        return reviewImages;
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void test(){
//        review = createReview();
//        Review save = reviewRepository.save(review);
//        System.out.println(save.getReviewImages().size());
//        System.out.println("dasdasd");
//    }
//}
//
