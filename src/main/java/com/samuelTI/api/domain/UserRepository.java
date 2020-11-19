package com.samuelTI.api.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samuelTI.api.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByLogin(String login);
	
	Optional<User> findByEmail(String email);
}
