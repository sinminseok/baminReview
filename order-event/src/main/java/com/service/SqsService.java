package com.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
//sns 토픽에서 메세지 가져오는 sqs 서비스
public class SqsService {
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SqsService(AmazonSQS amazonSqs) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSqs);
    }

    public void getMessage() {
        String rr = queueMessagingTemplate.receiveAndConvert("sqs-study", String.class);
        System.out.println("queue message :: " + rr );

    }
}