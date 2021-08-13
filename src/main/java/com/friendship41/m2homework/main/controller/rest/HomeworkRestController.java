package com.friendship41.m2homework.main.controller.rest;

import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.data.request.ReqCharacterHomework;
import com.friendship41.m2homework.main.data.request.ReqHomework;
import com.friendship41.m2homework.main.mapper.ReqHomeworkMapper;
import com.friendship41.m2homework.main.mapper.ResHomeworkMapper;
import com.friendship41.m2homework.main.service.HomeworkService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PostMapping("character")
  public Object postHomeworkCharacter(@RequestBody final ReqCharacterHomework reqCharacterHomework) {
    Homework homework = this.homeworkService
        .insertM2CharacterHomework(reqCharacterHomework.getCharacterNo(), reqCharacterHomework.getHomeworkNo())
        .getHomework();
    return this.resHomeworkMapper.toDto(homework);
  }

  @GetMapping("targetType/list")
  public Object getHomeworkTargetTypeList() {
    return this.homeworkService.getHomeworkTargetTypeList();
  }

  @GetMapping
  public Object getHomeworkList(
      @RequestParam(required = false, defaultValue = "0") final Integer page,
      @RequestParam(required = false, defaultValue = "10") final Integer size) {
    int memberNo = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.homeworkService
        .getHomeworkListByMember(memberNo, page, size)
        .map(this.resHomeworkMapper::toDto);
  }

  @GetMapping("character")
  public Object getHomeworkCharacterList(
      @RequestParam final Integer characterNo,
      @RequestParam(required = false, defaultValue = "0") final Integer page,
      @RequestParam(required = false, defaultValue = "10") final Integer size) {
    return this.homeworkService
        .getHomeworkListByCharacter(characterNo, page, size)
        .map(this.resHomeworkMapper::toDto);
  }

  @DeleteMapping("character")
  public Object deleteHomeworkCharacter(@RequestBody final ReqCharacterHomework reqCharacterHomework) {
    this.homeworkService
        .deleteM2CharacterHomework(reqCharacterHomework.getCharacterNo(), reqCharacterHomework.getHomeworkNo());
    return "{}";
  }
}
