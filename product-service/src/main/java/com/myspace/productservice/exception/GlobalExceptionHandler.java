package com.myspace.productservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.myspace.productservice.dto.ErrorResponse;
import com.myspace.productservice.util.Constants;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	 	@ExceptionHandler(ResourceNotFoundException.class)
	    public final ResponseEntity<ErrorResponse> handleResourceNotExceptions(Exception ex) {
	        ErrorResponse response = new ErrorResponse(Constants.FAILURE_CODE, ex.getLocalizedMessage());
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(BusniessException.class)
	    public final ResponseEntity<ErrorResponse> handleBusinessExceptions(BusniessException ex, WebRequest request) {
	        ErrorResponse errorDetails = new ErrorResponse(Constants.FAILURE_CODE, ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(ValidationException.class)
	    public final ResponseEntity<ErrorResponse> handleValidationExceptions(ValidationException ex, WebRequest request) {
	        ErrorResponse errorDetails = new ErrorResponse(Constants.FAILURE_CODE, ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }

	    @Override
	    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        JsonMappingException jme = (JsonMappingException) ex.getCause();
	        ErrorResponse errorDetails = new ErrorResponse(Constants.FAILURE_CODE, jme.getPath().get(0).getFieldName() + " invalid");
	        return new ResponseEntity<>(errorDetails, headers, status);
	    }

	    @Override
	    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
	            HttpRequestMethodNotSupportedException ex,
	            HttpHeaders headers,
	            HttpStatus status,
	            WebRequest request) {
	        StringBuilder builder = new StringBuilder();
	        builder.append(". Supported methods are ");
	        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
	        String supported = builder.toString();
	        ErrorResponse apiError = new ErrorResponse(Constants.FAILURE_CODE, ex.getLocalizedMessage() + supported);
	        return new ResponseEntity<>(apiError, headers, status);
	    }

	    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
	    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
	        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
	        ErrorResponse apiError = new ErrorResponse(Constants.FAILURE_CODE, ex.getLocalizedMessage() + error);
	        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	    }
	    
	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    	ErrorResponse errorDetails = new ErrorResponse(Constants.FAILURE_CODE, ex.getLocalizedMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }
}
