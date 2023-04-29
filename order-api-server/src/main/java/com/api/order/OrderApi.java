package com.api.order;


import com.dto.requestDto.order.OrderRequestDto;
import com.service.order.OrderRegisterService;
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
    private OrderRegisterService orderRegisterService;

    @PostMapping("/order")
    public ResponseEntity<Long> save(
            @Valid @RequestBody OrderRequestDto orderRequestDto
    ) throws Exception {

        Long register = orderRegisterService.register(orderRequestDto);
        return ResponseEntity.ok(register);

    }

}
