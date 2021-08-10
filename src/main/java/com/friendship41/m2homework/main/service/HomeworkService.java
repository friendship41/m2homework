package com.friendship41.m2homework.main.service;

import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.entity.MemberHomework;
import com.friendship41.m2homework.main.data.type.HomeworkTargetType;
import com.friendship41.m2homework.main.repository.HomeworkRepository;
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

@Service
@RequiredArgsConstructor
@Transactional
public class HomeworkService {
  private final MemberService memberService;
  private final HomeworkRepository homeworkRepository;
  private final MemberHomeworkRepository memberHomeworkRepository;

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
}
