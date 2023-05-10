package com.listener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsListener {

    //Spring boot 3.0.x 버전에서 SqsListener 사용이 불가능하다.
    @SqsListener(value = "sqs-study.fifo", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void listen(@Payload String sst, Acknowledgment ack) {
        ack.acknowledge();
    }
}