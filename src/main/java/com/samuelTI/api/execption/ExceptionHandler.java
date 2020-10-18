package com.samuelTI.api.execption;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<?> errorNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<?> errorBadRequest(Exception ex) {
		return ResponseEntity.badRequest().build();
	}
	
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders httpHeaders){
		return new ResponseEntity<>("Operaçao nao permitida" , HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}

class ExecptionError implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;
	
	ExecptionError(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
}
