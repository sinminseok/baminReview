//package com.config;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.sns.AmazonSNS;
//import com.amazonaws.services.sns.AmazonSNSClient;
//import com.amazonaws.services.sns.AmazonSNSClientBuilder;
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.AmazonSQSAsync;
//import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.StringUtils;
//import org.hibernate.cfg.Environment;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.sns.SnsClient;
//
//// -application.yml 에 있는 access-key , secret-key 값을 이용해 AWSCredentialsProvider 빈 생성
//// - Message 송신에 사용되는 AmazonSQS빈 생성시 해당 credentials 정보와 region 정보설정
//// - Message 수신시 사용되는 Listener 관련 설정
//@Getter
//@Configuration
//@Slf4j
//public class AwsConfig {
//    @Value("${cloud.aws.credentials.access-key}")
//    private String awsAccessKey;
//
//    @Value("${cloud.aws.credentials.secret-key}")
//    private String awsSecretKey;
//
//    @Value("${cloud.aws.region.static}")
//    private String awsRegion;
//
//    @Value("${cloud.aws.sns.topic.arn}")
//    private String snsTopicARN;
//
//    @Value("${cloud.aws.sqs.queue.url}")
//    private String sqsUrl;
//
//
//    @Bean
//    public QueueMessagingTemplate queueMessagingTemplate() {
//        return new QueueMessagingTemplate(amazonSQSAsync());
//    }
//
//    public AmazonSQSAsync amazonSQSAsync() {
//        return AmazonSQSAsyncClientBuilder.standard().withRegion(awsRegion)
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
//                .build();
//    }
//
//    @Bean // SNS Client 세팅
//    public SnsClient getSnsClient() {
//        return SnsClient.builder()
//                .credentialsProvider(
//                        getAwsCredentials(this.awsAccessKey, this.awsSecretKey)
//                ).region(Region.of(this.awsRegion))
//
//                .build();
//    }
//
//    //aws credential 세팅
//    public AwsCredentialsProvider getAwsCredentials(String accessKeyID, String secretAccessKey) {
//        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKeyID, secretAccessKey);
//        return () -> awsBasicCredentials;
//    }
//
//
//    @Bean // SQS Client 세팅
//
//    public AmazonSQS amazonSQS() {
//        AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
//        return AmazonSQSAsyncClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//
//                .withRegion(awsRegion)
//                .build();
//    }
//}