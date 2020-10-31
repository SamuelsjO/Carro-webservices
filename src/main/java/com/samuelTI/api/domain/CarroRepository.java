package com.samuelTI.api.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	List<Carro> findByTipo(String tipo, Pageable pageable);
	
	List<Carro> findByNomeContaining(String query);

}