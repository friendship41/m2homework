package com.friendship41.m2homework.main.service;

import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.repository.M2CharacterRepository;
import com.friendship41.m2homework.member.data.entity.Member;
import com.friendship41.m2homework.member.exception.MemberNotFoundException;
import com.friendship41.m2homework.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class M2CharacterService {
  private final MemberRepository memberRepository;
  private final M2CharacterRepository m2CharacterRepository;

  public Member insertCharacter(final M2Character m2Character) throws MemberNotFoundException {
    Member member = this.memberRepository.findById(m2Character.getMemberNo())
        .orElseThrow(() -> new MemberNotFoundException(m2Character.getMemberNo()));
    member.getM2Characters().add(m2Character);
    return member;
  }

  public List<M2Character> getM2CharacterList(final int memberNo) {
    return new ArrayList<>(this.memberRepository
        .findById(memberNo)
        .orElseThrow(() -> new MemberNotFoundException(memberNo))
        .getM2Characters());
  }
}
