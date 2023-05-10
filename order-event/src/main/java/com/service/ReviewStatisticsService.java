package com.service;


import com.entity.review.Review;
import com.repository.review.ReviewRepository;
import com.repository.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewStatisticsService {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;

    @Value("${cloud.aws.sqs.queue.url}")
    private String sqsUrl;

    @Scheduled(fixedDelay = 5000) // executes on every 5 second gap.
    public void receiveMessages() {

        Review receiveReview = queueMessagingTemplate.receiveAndConvert(sqsUrl, Review.class);

    }

    private void processInvoice(String body) {
        log.info("Processing invoice generation and sending invoice emails from here..");
    }
}
