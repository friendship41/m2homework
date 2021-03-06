package com.friendship41.m2homework.main.service;

import com.friendship41.m2homework.common.data.entity.ImageData;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.type.JobType;
import com.friendship41.m2homework.main.exception.CharacterNotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

  public Page<M2Character> getM2CharacterList(final Integer memberNo, final String characterName, final JobType jobType,
      final Boolean isMain, final Integer characterImageNo, final Integer page, final Integer size, final String sort) {
    return this.m2CharacterRepository.findByDynamicQuery(memberNo, characterName, jobType, isMain, characterImageNo,
        PageRequest.of(page, size, Sort.by(sort)));
  }

  public List<Map<String, Object>> getJobTypeList() {
    return Arrays.stream(JobType.values())
        .map(jobType -> new HashMap<String, Object>(Map.of(
            "code", jobType,
            "enName", jobType.getEnName(),
            "korName", jobType.getKorName())))
        .collect(Collectors.toList());
  }

  public M2Character updateCharacterImage(final int m2CharacterNo, final List<ImageData> imageDataList) {
    M2Character m2Character = this.m2CharacterRepository.findById(m2CharacterNo)
        .orElseThrow(() -> new CharacterNotFoundException(m2CharacterNo));

    if (imageDataList == null || imageDataList.isEmpty()) {
      return m2Character;
    }

    m2Character.setCharacterImageNo(imageDataList.get(0).getImageNo());
    return m2Character;
  }
}
