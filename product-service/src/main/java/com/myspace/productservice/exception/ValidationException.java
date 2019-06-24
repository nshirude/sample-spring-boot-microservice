package com.myspace.productservice.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    public int code;
    public ValidationException(String message) {
        super(message);
    }
    public ValidationException(String message , Throwable e) {
        super(message,e);
    }
    public ValidationException(String message , Throwable e, int code) {
        super(message,e);
        this.code = code;
    }
    public ValidationException( int code,String message) {
        super(message);
        this.code = code;
    }
}
