package com.repository.review;

import com.repositoryImpl.review.ReviewRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewRepositoryImpl reviewRepositoryImpl;


    @Test
    @Transactional
    @Rollback(value = false)
    public void test(){


    }
}

