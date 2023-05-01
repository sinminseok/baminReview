package com.entity.review;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    //변경x
    @Column(name = "member_number")
    private Long memberNumber;
    //변경x
    @Column(name = "order_id")
    private Long orderId;
    //변경x
    @Column(name = "shop_id")
    private Long shopId;

    //리뷰 내용
    @Column(name = "content")
    private String content;

    //별점
    @Column(name = "start_point")
    private double starPoint;

    //리뷰 이미지
    @OneToMany(mappedBy = "review",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    //메뉴 리뷰
    @OneToMany(mappedBy = "review",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<ReviewMenu> reviewMenus = new ArrayList<>();

    //배달 리뷰
    @OneToOne(mappedBy = "review",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private ReviewDelivery reviewDelivery;

    public void addReviewDelivery(ReviewDelivery reviewDelivery){
        this.reviewDelivery = reviewDelivery;

    }

    public void updateReview(Review review){
        this.content = review.content;
        this.starPoint = review.starPoint;
//        this.reviewImages = review.reviewImages;
//        this.reviewMenus = reviewMenus;
    }



}
