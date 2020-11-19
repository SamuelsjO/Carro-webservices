package com.samuelTI.api.jwt.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuelTI.api.domain.DadosLogin;
import com.samuelTI.api.domain.User;
import com.samuelTI.api.domain.UserRepository;
import com.samuelTI.api.execption.ExistedTokenException;
import com.samuelTI.api.execption.ExistingEmailException;
import com.samuelTI.api.execption.InvalidLoginException;

import io.jsonwebtoken.Claims;

@Service
public class UserAuthenticationService {

	private final UserRepository repository;
	private TokenService tokenService;
	
	@Autowired
	public UserAuthenticationService(UserRepository repository, TokenService tokenService) {
		this.repository = repository;
		this.tokenService = tokenService;
	}
	
	public User authenticate(DadosLogin dados, String token) {
		User user = repository.findByEmail(dados.getEmail()).orElseThrow(ExistingEmailException::new);
		if (dados.getSenha().equals(user.getSenha()) && !token.isEmpty() && validate(token)) {
			return user;
		} else {
			throw new InvalidLoginException();
		}

	}
	
	private boolean validate(String token) {
		try {
			String tokenTradado = token.replace("Bearer ", "");
			Claims claims = tokenService.decodeToken(tokenTradado);

			System.out.println(claims.getIssuer());
			System.out.println(claims.getIssuedAt());

			// verifica se o token está expirando
			if (claims.getExpiration().before(new Date(System.currentTimeMillis())))
				throw new ExistedTokenException();

			System.out.println(claims.getExpiration());

			return true;
		} catch (ExistedTokenException et) {
			et.printStackTrace();
			throw et;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidLoginException();
		}

	}
}