package com.friendship41.m2homework.main.service;

import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.entity.MemberHomework;
import com.friendship41.m2homework.main.data.type.HomeworkTargetType;
import com.friendship41.m2homework.main.data.type.JobType;
import com.friendship41.m2homework.main.repository.HomeworkRepository;
import com.friendship41.m2homework.main.repository.MemberHomeworkRepository;
import com.friendship41.m2homework.member.data.entity.Member;
import com.friendship41.m2homework.member.service.MemberService;
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

  public List<Homework> getHomeworkListByMember(final Integer memberNo) {
    return this.memberHomeworkRepository.findAllByMember(Member.builder().memberNo(memberNo).build()).stream()
        .map(MemberHomework::getHomework)
        .collect(Collectors.toList());
  }
}
