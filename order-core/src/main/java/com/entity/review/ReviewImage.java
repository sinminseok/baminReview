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
public class ReviewImage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_image_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    private String imageUrl;

    public void update(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
