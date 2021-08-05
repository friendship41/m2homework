package com.friendship41.m2homework.common.exception;

import com.friendship41.m2homework.common.data.type.AccessScope;

public class ImageAccessNotAllowedException extends BizException {
  public ImageAccessNotAllowedException(final int imageNo, final AccessScope targetScope) {
    super("image access not allow, imageNo: " + imageNo + ", targetScope: " + targetScope);
  }
}
