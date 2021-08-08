package com.friendship41.m2homework.temp.controller;

import com.friendship41.m2homework.main.data.entity.Homework;
import com.friendship41.m2homework.main.service.HomeworkService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TempController {
  @GetMapping("/temp")
  public String getTemp(HttpServletRequest request) {
    return "temp";
  }

  @PostMapping("/temp")
  public String postTemp(HttpServletRequest request) {
    return "temp";
  }
}
