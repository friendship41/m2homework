package com.friendship41.m2homework.member.repository;

import com.friendship41.m2homework.member.data.entity.Member;
import java.util.List;

public interface MemberRepositoryCustom {
  List<Member> findByMemberNoOrUserId(Integer memberNo, String userId);
}
