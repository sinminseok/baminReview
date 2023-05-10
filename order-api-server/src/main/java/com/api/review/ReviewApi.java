package com.api.review;


import com.dto.requestDto.order.OrderRequestDto;
import com.dto.requestDto.review.ReviewRequestDto;
import com.service.order.OrderService;
import com.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewApi {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Long> save(
            @Valid @RequestBody ReviewRequestDto reviewRequestDto
    ) throws Exception {

        Long register = reviewService.register(reviewRequestDto);
        return ResponseEntity.ok(register);

    }

}
