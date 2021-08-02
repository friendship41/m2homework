package com.friendship41.m2homework.auth.controller.mvc;

import com.friendship41.m2homework.member.data.entity.Member;
import com.friendship41.m2homework.member.exception.MemberIdConstraintException;
import com.friendship41.m2homework.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
  private final MemberService memberService;

  @GetMapping
  public String getRegisterPage() {
    return "auth/register";
  }

  @PostMapping
  public String postRegister(
      @RequestParam("memberId") final String memberId,
      @RequestParam("password") final String password,
      final Model model) {
    try {
      this.memberService.insertMember(Member.builder()
          .memberId(memberId)
          .password(password)
          .build());
      return "redirect:/auth/login";
    } catch (MemberIdConstraintException e) {
      model.addAttribute("errorCode", "500");
      model.addAttribute("memberId", e.getMemberId());
      return "auth/register";
    }
  }
}
