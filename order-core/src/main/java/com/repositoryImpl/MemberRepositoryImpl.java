package com.repositoryImpl;

import com.entity.member.Member;
import com.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repositoryCustom.member.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> findByMemberNumber(String memberNumber) {
        QMember member = QMember.member;
        return jpaQueryFactory.selectFrom(member).where(member.memberNumber.eq(memberNumber)).fetch();
    }
}
