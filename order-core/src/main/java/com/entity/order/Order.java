package com.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long memberNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<OrderMenu> orderMenus = new ArrayList<>();

    public void addOrderMenu(OrderMenu orderMenu){
        this.orderMenus.add(orderMenu);

    }
}
