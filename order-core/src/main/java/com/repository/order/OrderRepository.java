package com.repository.order;

import com.entity.order.Order;
import com.repositoryCustom.order.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>, OrderRepositoryCustom {
}
