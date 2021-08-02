package com.friendship41.m2homework.member.repository;

import com.friendship41.m2homework.member.data.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {
//  @EntityGraph(attributePaths = "m2Characters")
  Optional<Member> findByMemberNo(Integer memberNo);
//  @EntityGraph(attributePaths = "m2Characters")
  Optional<Member> findByMemberId(String memberId);
}
