package com.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

    //EntityManager를 빈으로 주입할대 사용하는 애노테이션 @PersistenceContext
    @PersistenceContext
    private EntityManager entityManager;

    //JPAQueryFactory를 Bean으로 등록해서 프로젝트 전역에서 사용할 수 있도록한다.
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
