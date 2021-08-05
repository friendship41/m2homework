package com.friendship41.m2homework.common.exception;

public class ImageNotFoundException extends BizException {
  public ImageNotFoundException(final int ImageNo) {
    super("image not found, imageNo: " + ImageNo);
  }

  public ImageNotFoundException(final int ImageNo, final String imageFullPath) {
    super("image not found, imageNo: " + ImageNo + ", imageFullPath: " + imageFullPath);
  }
}
