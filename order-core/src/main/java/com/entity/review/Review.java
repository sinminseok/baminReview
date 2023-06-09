package com.entity.review;
import com.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited(withModifiedFlag = true)
public class Review extends BaseEntity {

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

    //리뷰 등록시간
    private LocalDateTime localDateTime;

    //리뷰 좋아요
    private Long likeCount;


    //리뷰메뉴
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ReviewMenu> reviewMenus = new ArrayList<>();


    //리뷰 이미지
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ReviewImage> reviewImages = new ArrayList<>();


    //배달 리뷰
    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private ReviewDelivery reviewDelivery;

    public void addReviewDelivery(ReviewDelivery reviewDelivery) {
        this.reviewDelivery = reviewDelivery;
    }

    public void decreaseLike() {
        this.likeCount -= 1L;

    }

    public void increaseLike() {
        this.likeCount += 1L;
    }


    public void updateReview(Review review) {
        this.content = review.content;
        this.starPoint = review.starPoint;
    }


}
