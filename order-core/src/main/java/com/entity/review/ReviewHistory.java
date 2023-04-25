package com.entity.review;


import jakarta.persistence.*;

@Entity
public class ReviewHistory {

    @Id
    @Column(name = "review_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reviewId;

    private String content;

    private ReviewStatus reviewStatus;
}
