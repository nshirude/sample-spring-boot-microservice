package com.myspace.productservice.dto;

public class ErrorResponse {
	
	private Integer code;
    private String message;

    public ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ErrorResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
