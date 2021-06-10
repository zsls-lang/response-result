package com.zsls.common.exception;

import com.zsls.common.enums.CodeMessageEnum;

public class CustomException extends RuntimeException {

    private CodeMessageEnum codeMessageEnum;

    public CodeMessageEnum getCodeMessageEnum() {
        return codeMessageEnum;
    }

    public CustomException() {

    }

    public CustomException(CodeMessageEnum codeMessageEnum) {
        super(codeMessageEnum.getMessage());
        this.codeMessageEnum = codeMessageEnum;
    }

    public CustomException(String message, CodeMessageEnum codeMessageEnum) {
        super(message);
        this.codeMessageEnum = codeMessageEnum;
    }

    public CustomException(String message, Throwable cause, CodeMessageEnum codeMessageEnum) {
        super(message, cause);
        this.codeMessageEnum = codeMessageEnum;
    }

    public CustomException(Throwable cause, CodeMessageEnum codeMessageEnum) {
        super(cause);
        this.codeMessageEnum = codeMessageEnum;
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CodeMessageEnum codeMessageEnum) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.codeMessageEnum = codeMessageEnum;
    }
}