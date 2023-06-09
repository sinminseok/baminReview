package com.entity.review;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;



@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Audited(withModifiedFlag = true)
public class ReviewMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    private String menuName;

    public void update(String menuName){
        this.menuName = menuName;
    }

}
