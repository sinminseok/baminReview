package com.entity.review;


import jakarta.persistence.*;

@Entity
public class ReviewDelivery {

    @Id
    @Column(name = "review_delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Review review;

    @Enumerated(EnumType.STRING)
    private ReviewDeliveryStatus reviewDeliveryStatus;

    private String hateReason;

}
