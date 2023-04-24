package com.repository;


import com.entity.Manager.Manager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.repository.ManagerRepository.ManagerRepository;

@DataJpaTest
public class ManagerRepositoryTest {
    @Autowired
    ManagerRepository managerRepository;

    @Test
    public void add(){
        Manager manager = Manager.builder()
                .loginId("testId")
                .loginPw("testPw")
                .build();
        Manager save = managerRepository.save(manager);
        Assertions.assertThat(save.getLoginId()).isEqualTo("testId");
    }
}

