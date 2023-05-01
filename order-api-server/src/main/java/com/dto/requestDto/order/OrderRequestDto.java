package com.dto.requestDto.order;


import com.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private String memberNumber;

    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    private List<OrderMenuRequestDto> orderMenuRequestDtos = new ArrayList<>();
}
