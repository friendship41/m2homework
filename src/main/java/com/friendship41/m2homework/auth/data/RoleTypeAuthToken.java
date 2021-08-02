package com.friendship41.m2homework.auth.data;

import com.friendship41.m2homework.auth.data.type.RoleType;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RoleTypeAuthToken extends UsernamePasswordAuthenticationToken {
  private RoleType roleType;

  public RoleTypeAuthToken(final Object principal, final Object credentials) {
    super(principal, credentials);
    this.roleType = RoleType.NON;
  }

  public RoleTypeAuthToken(final Object principal, final Object credentials,
      final RoleType roleType, final Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
    this.roleType = roleType;
  }

  public RoleType getRoleType() {
    return roleType;
  }

  public void setRoleType(final RoleType roleType) {
    this.roleType = roleType;
  }
}
