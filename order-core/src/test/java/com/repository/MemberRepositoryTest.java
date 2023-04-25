package com.repository;


import com.entity.member.Member;
import com.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void add(){
        Member member = Member.builder()
                .memberNumber("testnumber")
                .build();
        Member save = memberRepository.save(member);
        Assertions.assertThat(save.getId()).isNotNull();

    }

    @Test
    public void querydsl설정기능확인(){
        Member member = Member.builder()
                .memberNumber("testnumber")
                .build();
        memberRepository.save(member);
        List<Member> testnumber = memberRepository.findByMemberNumber("testnumber");
        Assertions.assertThat(testnumber.size()).isEqualTo(1);
    }

}

