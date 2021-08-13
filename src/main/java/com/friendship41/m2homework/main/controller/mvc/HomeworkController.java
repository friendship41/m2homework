package com.friendship41.m2homework.main.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
  @GetMapping("list")
  public String getHomeworkListPage() {
    return "main/homeworkList";
  }

  @GetMapping("character")
  public String getHomeworkCharacterPage() {
    return "main/homeworkCharacter";
  }
}
