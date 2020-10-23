package com.samuelTI.api.jwt.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.samuelTI.api.jwt.ServletUtil;

@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UnauthorizedHandler.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.warn("unauthorizedHandler, exception: " + authException);
		
		//Chamando se token errado ou ausente
		String json = ServletUtil.getJson("error", "Não autorizado");
		ServletUtil.write(response, HttpStatus.FORBIDDEN, json);
		
	}

}
