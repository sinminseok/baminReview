package com.repositoryCustom.order;

import com.entity.order.Order;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> searchAllBymemberNumber(String memberNumber);
}
