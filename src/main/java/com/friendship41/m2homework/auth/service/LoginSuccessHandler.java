package com.friendship41.m2homework.auth.service;

import com.friendship41.m2homework.member.service.MemberService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  private final MemberService memberService;

  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
      final Authentication authentication) throws IOException {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    this.memberService.memberlogin(Integer.parseInt(authentication.getPrincipal().toString()));
    response.sendRedirect("/homework/list");
  }
}
