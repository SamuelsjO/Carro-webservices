package com.samuelTI.api.execption;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
		return new ResponseEntity<>(new ExecptionError("Operaçao nao permitida"), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<?> accessDenied() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso Negado"));
	}
}

class Error {
	public String error;
	
	public Error(String error) {
		this.error = error;
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
