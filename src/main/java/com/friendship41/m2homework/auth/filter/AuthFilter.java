package com.friendship41.m2homework.auth.filter;

import com.friendship41.m2homework.auth.data.type.RoleType;
import com.friendship41.m2homework.auth.data.RoleTypeAuthToken;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {
  public AuthFilter(final AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
      throws AuthenticationException {
    Object loginInfo = request.getSession().getAttribute("loginInfo");
    if (loginInfo == null) {
      return this.authenticationWithUsernameAndPassword(request);
    } else {
      return (Authentication) loginInfo;
    }
  }

  private Authentication authenticationWithUsernameAndPassword(final HttpServletRequest request) {
    RoleTypeAuthToken roleTypeAuthToken =
        new RoleTypeAuthToken(request.getParameter("username"), request.getParameter("password"));
    if (request.getRequestURI().equals("/login")) {
      String loginType = request.getParameter("loginType");
      if (loginType == null) {
        roleTypeAuthToken.setRoleType(RoleType.NON);
      } else {
        roleTypeAuthToken.setRoleType(RoleType.valueOf(loginType.toUpperCase()));
      }
    }

    setDetails(request, roleTypeAuthToken);
    Authentication authentication = this.getAuthenticationManager().authenticate(roleTypeAuthToken);
    request.getSession().setAttribute("loginInfo", authentication);
    return authentication;
  }
}
