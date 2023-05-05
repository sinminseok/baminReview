package com.service;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.config.AwsConfig;
import com.dto.EcmDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.Map;

@Service
@RequiredArgsConstructor
//지정한 topic으로 메세지 발행하는 서비스
public class SnsService {
    private final AwsConfig awsConfig;

    public PublishResponse awsSnsPublishTest(Map<String,Object> scriptData) {
        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(awsConfig.getSnsTopicARN())
                .subject("TEST 제목")
                .message(scriptData.toString())
                .messageGroupId("sns-group")

                .build();


        SnsClient snsClient = awsConfig.getSnsClient();
        PublishResponse publishResponse = snsClient.publish(publishRequest);

        snsClient.close();
        return publishResponse;
    }


}