package com.service.order;


import com.config.MapperConfig;
import com.dto.requestDto.order.OrderMenuRequestDto;
import com.dto.requestDto.order.OrderRequestDto;
import com.entity.order.Order;
import com.entity.order.OrderMenu;
import com.entity.order.OrderStatus;
import com.repository.order.OrderMenuRepository;
import com.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderRegisterService {
    private final OrderRepository orderRepository;
    private final MapperConfig mapperConfig;
    private
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    //루트 애그리거트인 Order에서 OrderMenu까지 관리
    public Long register(OrderRequestDto orderRequestDto){
        //Order 엔티티 생성
        Order order = Order.builder()
                .orderStatus(OrderStatus.DURING)
                .orderTime(LocalDateTime.now())
                .memberNumber(orderRequestDto.getMemberNumber())
                .orderMenus(new ArrayList<>())
                .build();
        createOrderMenus(orderRequestDto,order);
        Order save = orderRepository.save(order);
        return save.getId();
    }




    public void createOrderMenus(OrderRequestDto orderRequestDto,Order order){
        for(OrderMenuRequestDto element : orderRequestDto.getOrderMenuRequestDtos()){
            OrderMenu orderMenu = OrderMenu.builder()
                    .menuName(element.getMenuName())
                    .order(order)
                    .build();
            order.getOrderMenus().add(orderMenu);
        }
    }
}
