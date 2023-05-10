package com.service.order;


import com.config.MapperConfig;
import com.dto.requestDto.order.OrderMenuRequestDto;
import com.dto.requestDto.order.OrderRequestDto;
import com.dto.responseDto.order.OrderMenuResponseDto;
import com.dto.responseDto.order.OrderResponseDto;
import com.entity.order.Order;
import com.entity.order.OrderMenu;
import com.entity.order.OrderStatus;
import com.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;


    //루트 애그리거트인 Order에서 OrderMenu까지 관리
    public Long register(OrderRequestDto orderRequestDto) {
        //Order 엔티티 생성
        Order order = Order.builder()
                .orderStatus(OrderStatus.DURING)
                .orderTime(LocalDateTime.now())
                .memberNumber(orderRequestDto.getMemberNumber())
                .orderMenus(new ArrayList<>())
                .build();
        createOrderMenus(orderRequestDto, order);
        Order save = orderRepository.save(order);
        return save.getId();
    }

//    public List<OrderResponseDto> findall(String memberNumber) {
//        List<Order> orders = orderRepository.searchAllBymemberNumber(memberNumber);
//        return changeOrderDtoList(orders);
//    }

    public OrderResponseDto findByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 주문입니다."));
        return changeOrderDto(order);
    }

    public OrderResponseDto changeOrderDto(Order order) {
        List<OrderMenuResponseDto> orderMenuResponseDtos = new ArrayList<>();
        for (OrderMenu orderMenu : order.getOrderMenus()) {
            orderMenuResponseDtos.add(modelMapper.map(orderMenu, OrderMenuResponseDto.class));
        }
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderTime(order.getOrderTime())
                .orderMenus(orderMenuResponseDtos)
                .build();
        return orderResponseDto;
    }

    public List<OrderResponseDto> changeOrderDtoList(List<Order> orders) {
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for (Order element : orders) {
            List<OrderMenuResponseDto> orderMenuResponseDtos = new ArrayList<>();
            for (OrderMenu orderMenu : element.getOrderMenus()) {
                orderMenuResponseDtos.add(modelMapper.map(orderMenu, OrderMenuResponseDto.class));
            }

            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .id(element.getId())
                    .orderStatus(element.getOrderStatus())
                    .orderTime(element.getOrderTime())
                    .orderMenus(orderMenuResponseDtos)
                    .build();
            orderResponseDtos.add(orderResponseDto);
        }
        return orderResponseDtos;

    }


    public void createOrderMenus(OrderRequestDto orderRequestDto, Order order) {
        for (OrderMenuRequestDto element : orderRequestDto.getOrderMenuRequestDtos()) {
            OrderMenu orderMenu = OrderMenu.builder()
                    .menuName(element.getMenuName())
                    .order(order)
                    .build();
            order.getOrderMenus().add(orderMenu);
        }
    }
}
