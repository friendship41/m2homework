package com.friendship41.m2homework.auth.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
      final AuthenticationException exception) throws IOException {
    response.sendRedirect("/login/fail");
  }
}
