package com.entity.review;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
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


}
