package com.api.order;


import com.dto.requestDto.order.OrderRequestDto;
import com.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Long> save(
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) throws Exception {

        Long register = orderService.register(orderRequestDto);
        return ResponseEntity.ok(register);

    }

}
