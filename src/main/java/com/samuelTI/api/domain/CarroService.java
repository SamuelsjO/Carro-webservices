package com.samuelTI.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;
	
	
	public Iterable<Carro> getCarros(){
		return repository.findAll();
		
	}
	
	public Optional<Carro> getCarrosById(Long id) {
		return repository.findById(id);
	}
	
	public Iterable<Carro> getCarrosByTipo(String tipo) {
		
		return repository.findByTipo(tipo);
	}
	
	public Carro insert(Carro carro) { 
		Assert.isNull(carro.getId() , "Não foi possivel inserir o registro");
		return repository.save(carro); 
	}
	
	public Carro update(Carro carro, Long id) {
		Assert.isNull(carro.getId() , "Não foi possivel atualizar o registro");
		
		//Busca o carro no banco de dados
		Optional<Carro> optional = getCarrosById(id);
		if(optional.isPresent()){
			Carro  db = optional.get();
			//Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());
			//atualiza o carro
			repository.save(db);
			return db;
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
		
	}
	
	public void delete(Long id) {
		Optional<Carro> carro = getCarrosById(id);
		if(carro.isPresent()) {
			repository.deleteById(id);
		}
		
	}
	
	public List<Carro> getCarrosFakes(){
		List<Carro> carros =  new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(1L, "Brasilia"));
		carros.add(new Carro(1L, "Corovete"));
		carros.add(new Carro(1L, "Chevete"));

		return carros;
	}

}
