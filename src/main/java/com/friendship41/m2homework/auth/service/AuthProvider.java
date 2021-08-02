package com.friendship41.m2homework.auth.service;

import com.friendship41.m2homework.auth.data.M2UserDetails;
import com.friendship41.m2homework.auth.data.RoleTypeAuthToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AuthProvider implements AuthenticationProvider {
  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    RoleTypeAuthToken roleTypeAuthToken = (RoleTypeAuthToken) authentication;

    switch (roleTypeAuthToken.getRoleType()) {
      case MEMBER:
      case GUILD_MASTER:
      case ADMIN:
        return this.processMemberAuthProvider(roleTypeAuthToken);
      case NON:
      default:
        return null;
    }
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return authentication.equals(RoleTypeAuthToken.class);
  }

  private Authentication processMemberAuthProvider(RoleTypeAuthToken roleTypeAuthToken) {
    M2UserDetails m2UserDetails =
        (M2UserDetails) this.userDetailsService.loadUserByUsername(roleTypeAuthToken.getName());

    if (!bCryptPasswordEncoder.matches((String) roleTypeAuthToken.getCredentials(), m2UserDetails.getPassword())) {
      throw new BadCredentialsException("invalid password, id: " + roleTypeAuthToken.getName());
    }

    // TODO: 길마, admin 관련 처리

    return new RoleTypeAuthToken(
        m2UserDetails.getMemberNo(),
        m2UserDetails.getUsername(),
        m2UserDetails.getRoleType(),
        m2UserDetails.getAuthorities());
  }
}
