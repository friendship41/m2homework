package com.friendship41.m2homework.auth.service;

import com.friendship41.m2homework.auth.data.M2UserDetails;
import com.friendship41.m2homework.member.data.entity.Member;
import com.friendship41.m2homework.member.service.MemberService;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final MemberService memberService;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    Member member = memberService.getMember(null, username);
    return new M2UserDetails(member, Collections.singleton(new SimpleGrantedAuthority(member.getRoleType().name())));
  }
}
