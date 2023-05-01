package com.entity.review;


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
public class ReviewDelivery {

    @Id
    @Column(name = "review_delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "review_id")
    private Review review;

    @Enumerated(EnumType.STRING)
    private ReviewDeliveryStatus reviewDeliveryStatus;

    private String hateReason;

    public void update(ReviewDeliveryStatus reviewDeliveryStatus,String hateReason){
        this.reviewDeliveryStatus = reviewDeliveryStatus;
        this.hateReason = hateReason;
    }



}
