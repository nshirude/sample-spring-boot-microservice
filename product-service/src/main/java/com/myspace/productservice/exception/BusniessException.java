package com.myspace.productservice.exception;

public class BusniessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
    public int code;
    public BusniessException(String message) {
        super(message);
    }
    public BusniessException(String message , Throwable e) {
        super(message,e);
    }
    public BusniessException(String message , Throwable e, int code) {
        super(message,e);
        this.code = code;
    }
    public BusniessException(String message, int code) {
        super(message);
        this.code = code;
    }
}
