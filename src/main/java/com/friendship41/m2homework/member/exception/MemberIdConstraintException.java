package com.friendship41.m2homework.member.exception;

import com.friendship41.m2homework.common.exception.BizException;
import org.springframework.dao.DataIntegrityViolationException;
import lombok.Getter;

@Getter
public class MemberIdConstraintException extends DataIntegrityViolationException implements BizException {
  private final String memberId;

  public MemberIdConstraintException(final String msg, final String memberId) {
    super(msg);
    this.memberId = memberId;
  }

  public MemberIdConstraintException(final String msg, final Throwable cause, final String memberId) {
    super(msg, cause);
    this.memberId = memberId;
  }
}
