package com.service.order;


import com.dto.requestDto.order.OrderMenuRequestDto;
import com.dto.requestDto.order.OrderRequestDto;
import com.dto.responseDto.order.OrderResponseDto;
import com.entity.order.Order;
import com.entity.order.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class OrderRegisterServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    @Transactional
    @Rollback(value = false)
    public void order등록테스트(){
        OrderRequestDto orderRequestDto = createOrderRequestDto();
        Long register = orderService.register(orderRequestDto);
        Assertions.assertThat(register).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 모든order조회byMemberNumber(){
        OrderRequestDto orderRequestDto = createOrderRequestDto();
        Long register = orderService.register(orderRequestDto);

        orderService.findall("memberNumber");
    }

    public OrderRequestDto createOrderRequestDto(){
        return OrderRequestDto.builder()
                .memberNumber("memberNumber")
                .orderStatus(OrderStatus.DURING)
                .orderTime(LocalDateTime.now())
                .orderMenuRequestDtos(createOrderMenuRequestDtos())
                .build();
    }

    public List<OrderMenuRequestDto> createOrderMenuRequestDtos(){
        List<OrderMenuRequestDto> orderMenuRequestDtos = new ArrayList<>();
        for(int i=0;i<3;i++){
            orderMenuRequestDtos.add(OrderMenuRequestDto.builder().menuName("menu이름"+i).build());
        }
        return orderMenuRequestDtos;
    }
}
