package com.entity.history.review;


import com.entity.review.ReviewStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewHistory {

    @Id
    @Column(name = "review_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reviewId;

    private String content;

    private ReviewStatus reviewStatus;
}
