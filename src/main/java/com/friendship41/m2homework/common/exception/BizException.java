package com.friendship41.m2homework.common.exception;

public class BizException extends RuntimeException {
  public BizException() {
  }

  public BizException(final String message) {
    super(message);
  }

  public BizException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BizException(final Throwable cause) {
    super(cause);
  }

  public BizException(final String message, final Throwable cause, final boolean enableSuppression,
      final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
