package com.samuelTI.api.jwt.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.samuelTI.api.jwt.ServletUtil;

/**
 * 
 * @author soliveira
 * Classe chamada quando acontece o erro 403 - FORBIDDEN
 */

@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler{

	@Override
	public void handle(
			HttpServletRequest request, 
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			String json = ServletUtil.getJson("error", "Aesso negado. ");
			ServletUtil.write(response, HttpStatus.FORBIDDEN, json);
		}
		
	}

	

}
