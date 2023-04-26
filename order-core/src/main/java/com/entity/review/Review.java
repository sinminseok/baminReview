package com.entity.review;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "member_number")
    private Long memberNumber;

    @NotNull
    @Column(name = "order_id")
    private Long orderId;

    @NotNull
    @Column(name = "shop_id")
    private Long shopId;

    @NotNull
    @Column(name = "content")
    private String content;

    //별점
    @Column(name = "start_point")
    private double starPoint;

    @OneToMany(mappedBy = "review",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @OneToMany(mappedBy = "review",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReviewMenu> reviewMenus = new ArrayList<>();

    @OneToOne(mappedBy = "review")
    private ReviewDelivery reviewDelivery;

//    //entity에서 연관관계 entity 등록시 entity로 받아서 저장한다.
//    public void registerReviewImg(List<ReviewImage> reviewImages){
//        this.reviewImages = reviewImages;
//    }
//
//    public void registerReviewMenu(List<ReviewMenu> reviewMenus){
//        this.reviewMenus = reviewMenus;
//    }
//
//    public void registerReviewDelivery(ReviewDelivery reviewDelivery){
//        this.reviewDelivery = reviewDelivery;
//    }


}
