package com.avoole.im.exception;

public class WXRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 484854326851927161L;

    public WXRuntimeException(String detailMessage) {
        super(detailMessage);
    }

    public WXRuntimeException() {
    }
}
