package com.entity.review;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;



@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Audited(withModifiedFlag = true)
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_like_id")
    private Long id;

    @Column(name = "member_number")
    private Long memberNumber;

    @Column(name = "review_id")
    private Long reviewId;

//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "review_id")
//    private Review review;
}
