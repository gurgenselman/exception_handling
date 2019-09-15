package com.selman.exceptionhandling.exception.type;


import com.selman.exceptionhandling.exception.error.ErrorCode;

public abstract class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 7798188493246895976L;

    private final transient ErrorCode errorCode;
    private Object[] params;

    public BaseRuntimeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    public BaseRuntimeException(ErrorCode errorCode, Throwable t) {
        super(t);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    /*public BaseRuntimeException(ErrorCode error, String message, Object... params) {
        this.error = error;
        this.message = message;
        this.params = params;
        if (params != null && params.length > 0) {
            this.message = String.format(message, (Object[]) params);
        }
    }
    
    public BaseRuntimeException(String message, ErrorCode error, Object... params) {
    	this(error, message, params);
    }*/

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object[] getParams() {
        return params;
    }
}
