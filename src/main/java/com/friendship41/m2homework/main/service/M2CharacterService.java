package com.friendship41.m2homework.main.service;

import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.type.JobType;
import com.friendship41.m2homework.main.repository.M2CharacterRepository;
import com.friendship41.m2homework.member.data.entity.Member;
import com.friendship41.m2homework.member.exception.MemberNotFoundException;
import com.friendship41.m2homework.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class M2CharacterService {
  private final MemberRepository memberRepository;
  private final M2CharacterRepository m2CharacterRepository;

  public M2Character insertCharacter(final M2Character m2Character) throws MemberNotFoundException {
    Member member = this.memberRepository.findById(m2Character.getMemberNo())
        .orElseThrow(() -> new MemberNotFoundException(m2Character.getMemberNo()));
    member.getM2Characters().add(m2Character);
    m2Character.setMemberNo(member.getMemberNo());
    return m2Character;
  }

  public List<M2Character> getM2CharacterList(final int memberNo) {
    return new ArrayList<>(this.memberRepository
        .findById(memberNo)
        .orElseThrow(() -> new MemberNotFoundException(memberNo))
        .getM2Characters());
  }

  public List<M2Character> getM2CharacterIsMainList(final int memberNo, final boolean isMain) {
    return this.m2CharacterRepository.findM2CharacterByMemberNoAndIsMain(memberNo, isMain);
  }

  public List<Map<String, Object>> getJobTypeList() {
    return Arrays.stream(JobType.values())
        .map(jobType -> new HashMap<String, Object>(Map.of(
            "code", jobType,
            "enName", jobType.getEnName(),
            "korName", jobType.getKorName())))
        .collect(Collectors.toList());
  }
}
