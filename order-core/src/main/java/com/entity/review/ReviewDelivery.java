package com.entity.review;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited(withModifiedFlag = true)
public class ReviewDelivery {

    @Id
    @Column(name = "review_delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
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
