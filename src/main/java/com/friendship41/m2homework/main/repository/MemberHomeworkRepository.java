package com.friendship41.m2homework.main.repository;

import com.friendship41.m2homework.main.data.entity.MemberHomework;
import com.friendship41.m2homework.member.data.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberHomeworkRepository extends JpaRepository<MemberHomework, Integer> {
  List<MemberHomework> findAllByMember(Member member);
}
