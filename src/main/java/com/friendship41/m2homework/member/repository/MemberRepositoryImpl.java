package com.friendship41.m2homework.member.repository;

import static com.friendship41.m2homework.member.data.entity.QMember.member;
import com.friendship41.m2homework.member.data.entity.Member;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class MemberRepositoryImpl implements MemberRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public List<Member> findByMemberNoOrUserId(Integer memberNo, String userId) {
    JPAQuery<Member> memberJPAQuery = queryFactory.selectFrom(member);
    if (memberNo != null) {
      memberJPAQuery.where(member.memberNo.eq(memberNo));
    }
    if (userId != null) {
      memberJPAQuery.where(member.memberId.eq(userId));
    }
    return memberJPAQuery.fetchJoin().fetch();
  }
}
