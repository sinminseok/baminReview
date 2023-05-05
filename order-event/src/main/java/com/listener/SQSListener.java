package com.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SQSListener {

    @SqsListener(value = "sqs-study", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(@Payload String sst, Acknowledgment ack) {
        log.info("{}", sst);
        ack.acknowledge();
    }
}