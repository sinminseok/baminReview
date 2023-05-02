package com.repositoryImpl.order;

import com.entity.order.Order;
import com.entity.order.QOrder;
import com.entity.order.QOrderMenu;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repositoryCustom.order.OrderRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QOrder order = QOrder.order;
    private QOrderMenu orderMenu = QOrderMenu.orderMenu;

    @Override
    public List<Order> searchAllBymemberNumber(String memberNumber) {
        return jpaQueryFactory.selectFrom(order)
                .join(order.orderMenus).fetchJoin()
                .where(order.memberNumber.eq(memberNumber))
                .fetch();
    }
}
