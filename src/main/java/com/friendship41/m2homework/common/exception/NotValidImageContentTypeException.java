package com.friendship41.m2homework.common.exception;

public class NotValidImageContentTypeException extends BizException {
    public NotValidImageContentTypeException(final String originalFilename, final String contentType) {
        super("not valie image file ext, originalFilename: " + originalFilename + ", contentType: " + contentType);
    }
}
