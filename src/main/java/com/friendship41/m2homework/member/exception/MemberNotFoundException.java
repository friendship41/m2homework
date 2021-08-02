package com.friendship41.m2homework.member.exception;

import com.friendship41.m2homework.common.exception.BizException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MemberNotFoundException extends UsernameNotFoundException implements BizException {
  public MemberNotFoundException(Integer memberNo) {
    super("member not found, memberNo: " + memberNo);
  }

  public MemberNotFoundException(Integer memberNo, String memberId) {
    super("member not found, memberNo: " + memberNo + ", memberId: " + memberId);
  }

  public MemberNotFoundException(final String msg) {
    super(msg);
  }

  public MemberNotFoundException(final String msg, final Throwable cause) {
    super(msg, cause);
  }
}
