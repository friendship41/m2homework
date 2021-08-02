package com.friendship41.m2homework.main.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/character")
public class CharacterController {
  @GetMapping("list")
  public String getCharacterList() {
    return "main/characterList";
  }
}
