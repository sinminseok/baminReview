package com;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;


//@EnableJpaRepositories(repositoryFactoryBeanClass = .class)
@SpringBootApplication
@EnableJpaAuditing
public class OrderApiApplication {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApiApplication.class, args);
    }


    //배포시 Spring 환경에선 localdate가 배포환경 시간에 맞춰 localdate를 호출한다.때문에 서울로 Timezone을 지정해줘야한다.
    //@PostConstruct 어노테이션은 의존성 주입이 완료된후 실행된다. bean lifeCycle에서 한번만 실행된다.
    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
