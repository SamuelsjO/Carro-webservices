package com.samuelTI.api.jwt.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.samuelTI.api.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final long expirationTime = 1800000;
	private String key = "String aleatoria Secret";
	
	public String generatetoken(User user) {
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject("Test Jwt Api")
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

	public Claims decodeToken(String token) {

		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
}
