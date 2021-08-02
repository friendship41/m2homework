package com.friendship41.m2homework.auth.controller.mvc;

import com.friendship41.m2homework.auth.data.RoleTypeAuthToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
  @GetMapping("page")
  public String getLoginPage() {
    if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
      log.info("no auth");
      return "auth/login";
    }

    RoleTypeAuthToken roleTypeAuthToken = (RoleTypeAuthToken) SecurityContextHolder.getContext().getAuthentication();
    switch (roleTypeAuthToken.getRoleType()) {
      case NON:
        break;
      case MEMBER:
      case GUILD_MASTER:
      case ADMIN:
        // TODO: login 모달...?
        return "temp";
    }
    log.info("LOGIN PAGE");
    return "auth/login";
  }

  @GetMapping("fail")
  public String getLoginFail() {
    return "auth/fail";
  }
}
