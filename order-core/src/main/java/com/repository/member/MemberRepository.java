package com.repository.member;

import com.entity.member.Member;
import com.repositoryCustom.member.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> , MemberRepositoryCustom {
}
