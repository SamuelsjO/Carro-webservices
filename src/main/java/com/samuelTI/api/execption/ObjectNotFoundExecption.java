package com.samuelTI.api.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =  HttpStatus.NOT_FOUND)
public class ObjectNotFoundExecption extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExecption(String message) {
		super(message);
	}
	
	public ObjectNotFoundExecption(String message, Throwable cause) {
		super(message, cause);
	}
}
