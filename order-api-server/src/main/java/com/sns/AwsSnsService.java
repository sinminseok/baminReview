package com.sns;

import com.dto.requestDto.review.ReviewDeliveryRequestDto;
import com.entity.review.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import software.amazon.awssdk.services.sns.model.PublishResponse;

public interface AwsSnsService {

    PublishResponse createSns(Review scriptData) throws JsonProcessingException;
}
