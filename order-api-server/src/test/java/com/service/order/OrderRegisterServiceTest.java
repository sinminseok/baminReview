package com.service.order;


import com.dto.requestDto.order.OrderMenuRequestDto;
import com.dto.requestDto.order.OrderRequestDto;
import com.entity.order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class OrderRegisterServiceTest {

    @Autowired
    private OrderRegisterService orderRegisterService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void order등록테스트(){
        OrderRequestDto orderRequestDto = createOrderRequestDto();
        Long register = orderRegisterService.register(orderRequestDto);
    }

    public OrderRequestDto createOrderRequestDto(){
        return OrderRequestDto.builder()
                .memberNumber(3L)
                .orderStatus(OrderStatus.DURING)
                .orderTime(LocalDateTime.now())
                .orderMenuRequestDtos(createOrderMenuRequestDtos())
                .build();
    }

    public List<OrderMenuRequestDto> createOrderMenuRequestDtos(){
        return Collections.singletonList(OrderMenuRequestDto.builder().menuName("menu이름").build());
    }
}
