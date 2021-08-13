package com.friendship41.m2homework.main.service;

import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.entity.M2CharacterHomework;
import com.friendship41.m2homework.main.data.entity.MemberHomework;
import com.friendship41.m2homework.main.data.type.HomeworkTargetType;
import com.friendship41.m2homework.main.exception.CharacterHomeworkNotFoundException;
import com.friendship41.m2homework.main.repository.HomeworkRepository;
import com.friendship41.m2homework.main.repository.M2CharacterHomeworkRepository;
import com.friendship41.m2homework.main.repository.M2CharacterRepository;
import com.friendship41.m2homework.main.repository.MemberHomeworkRepository;
import com.friendship41.m2homework.member.data.entity.Member;
import com.friendship41.m2homework.member.service.MemberService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HomeworkService {
  private final MemberService memberService;

  private final M2CharacterRepository m2CharacterRepository;
  private final HomeworkRepository homeworkRepository;
  private final MemberHomeworkRepository memberHomeworkRepository;
  private final M2CharacterHomeworkRepository m2CharacterHomeworkRepository;

  public Homework insertHomeworkByMemberHomework(final int memberNo, final Homework homework) {
    return this.memberHomeworkRepository.save(MemberHomework.builder()
            .member(this.memberService.getMember(memberNo, null))
            .homework(homework)
            .build())
        .getHomework();
  }

  public List<Map<String, Object>> getHomeworkTargetTypeList() {
    return Arrays.stream(HomeworkTargetType.values())
        .map(homeworkTargetType -> new HashMap<String, Object>(Map.of(
            "code", homeworkTargetType,
            "enName", homeworkTargetType.getEnName(),
            "korName", homeworkTargetType.getKorName())))
        .collect(Collectors.toList());
  }

  public Page<Homework> getHomeworkListByMember(
      final Integer memberNo, final Integer page, final Integer size) {
    return this.memberHomeworkRepository
        .findAllByMember(Member.builder().memberNo(memberNo).build(), PageRequest.of(page, size))
        .map(MemberHomework::getHomework);
  }

  public Page<Homework> getHomeworkListByCharacter(final Integer characterNo, final Integer page, final Integer size) {
    return this.m2CharacterHomeworkRepository
        .findAllByM2Character(M2Character.builder().characterNo(characterNo).build(), PageRequest.of(page, size))
        .map(M2CharacterHomework::getHomework);
  }

  public M2CharacterHomework insertM2CharacterHomework(final int characterNo, final int homeworkNo) {
    List<M2CharacterHomework> m2CharacterHomeworkList =
        this.m2CharacterHomeworkRepository.findAllByM2CharacterAndHomework(
            M2Character.builder().characterNo(characterNo).build(),
            Homework.builder().homeworkNo(homeworkNo).build());
    if (m2CharacterHomeworkList != null && !m2CharacterHomeworkList.isEmpty()) {
      log.warn("duplicate character homework!, m2CharacterHomework={}", m2CharacterHomeworkList);
      return m2CharacterHomeworkList.get(0);
    }

    return this.m2CharacterHomeworkRepository.save(
        new M2CharacterHomework(
            null,
            this.m2CharacterRepository.findById(characterNo)
                .orElseThrow(),
            this.homeworkRepository.findById(homeworkNo)
                .orElseThrow()));
  }

  public void deleteM2CharacterHomework(final int characterNo, final int homeworkNo) {
    List<M2CharacterHomework> m2CharacterHomeworkList =
        this.m2CharacterHomeworkRepository.findAllByM2CharacterAndHomework(
            M2Character.builder().characterNo(characterNo).build(),
            Homework.builder().homeworkNo(homeworkNo).build());
    if (m2CharacterHomeworkList == null || m2CharacterHomeworkList.isEmpty()) {
      throw new CharacterHomeworkNotFoundException(characterNo, homeworkNo);
    }
    m2CharacterHomeworkRepository.deleteAll(m2CharacterHomeworkList);
  }
}
