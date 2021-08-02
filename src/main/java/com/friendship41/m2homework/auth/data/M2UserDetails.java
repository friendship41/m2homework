package com.friendship41.m2homework.auth.data;

import com.friendship41.m2homework.auth.data.type.RoleType;
import com.friendship41.m2homework.member.data.entity.Member;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class M2UserDetails implements UserDetails {
  private final Member member;
  private final Collection<? extends GrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getUsername() {
    return this.member.getMemberId();
  }

  public int getMemberNo() {
    return this.member.getMemberNo();
  }

  @Override
  public String getPassword() {
    return this.member.getPassword();
  }

  public RoleType getRoleType() {
    return this.member.getRoleType();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
