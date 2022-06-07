package com.practice.dManage.exception;

import lombok.Getter;

@Getter
public class DManageException extends RuntimeException{
    private DManageErrorCode dManageErrorCode;
    private String detailMessage;

    public DManageException(DManageErrorCode errorCode) {
        super(errorCode.getMessage());
        this.dManageErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public DManageException(DManageErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.dManageErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
