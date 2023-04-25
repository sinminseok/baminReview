package com.repositoryCustom.member;

import com.entity.member.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findByMemberNumber(String memberNumber);
}
