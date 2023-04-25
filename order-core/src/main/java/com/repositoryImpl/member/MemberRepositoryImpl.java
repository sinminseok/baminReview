package com.repositoryImpl.member;

import com.entity.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repositoryCustom.member.MemberRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> searchByName(String name) {
//        jpaQueryFactory
//                .selectFrom()
        return null;
    }
}
