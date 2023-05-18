package com.sns;

import com.config.AwsConfig;
import com.entity.review.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;


@Service
@RequiredArgsConstructor
public class ReviewSnsService implements AwsSnsService {

    private final AwsConfig awsConfig;
    private final ObjectMapper objectMapper;


    @Override
    public PublishResponse createSns(Review scriptData) throws JsonProcessingException {

        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(awsConfig.getSnsTopicARN())
                .subject("Review Event!")
                .message(objectMapper.writeValueAsString(scriptData))
                .messageGroupId("sns-group")
                .build();

        SnsClient snsClient = awsConfig.getSnsClient();
        PublishResponse publishResponse = snsClient.publish(publishRequest);

        snsClient.close();

        return publishResponse;
    }
}
