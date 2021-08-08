package com.friendship41.m2homework.main.controller.rest;

import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.request.ReqHomework;
import com.friendship41.m2homework.main.mapper.ReqHomeworkMapper;
import com.friendship41.m2homework.main.mapper.ResHomeworkMapper;
import com.friendship41.m2homework.main.service.HomeworkService;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/homework")
@RequiredArgsConstructor
public class HomeworkRestController {
  private final HomeworkService homeworkService;
  private final ReqHomeworkMapper reqHomeworkMapper;
  private final ResHomeworkMapper resHomeworkMapper;

  @PostMapping
  public Object postHomework(@RequestBody final ReqHomework reqHomework) {
    int memberNo = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Homework homework =
        this.homeworkService.insertHomeworkByMemberHomework(memberNo, this.reqHomeworkMapper.toEntity(reqHomework));
    return this.resHomeworkMapper.toDto(homework);
  }

  @GetMapping("targetType/list")
  public Object getHomeworkTargetTypeList() {
    return this.homeworkService.getHomeworkTargetTypeList();
  }

  @GetMapping
  public Object getHomeworkList() {
    int memberNo = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.homeworkService.getHomeworkListByMember(memberNo).stream()
        .map(this.resHomeworkMapper::toDto)
        .collect(Collectors.toList());
  }
}
