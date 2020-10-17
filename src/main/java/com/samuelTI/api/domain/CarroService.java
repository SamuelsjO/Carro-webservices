package com.samuelTI.api.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.samuelTI.api.domain.dto.CarroDTO;
import com.samuelTI.api.execption.ObjectNotFoundExecption;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;

	public List<CarroDTO> getCarros() {

		return repository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	
	}

	public CarroDTO getCarrosById(Long id) {
		return repository.findById(id).map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundExecption("Carro não encotrado!!"));
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {

		return repository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
		return CarroDTO.create(repository.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {

		// Busca o carro no banco de dados
		Optional<Carro> optional = repository.findById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();
			// Copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());

			// atualiza o carro
			repository.save(db);

			return CarroDTO.create(db);
		} else {
			return null;

		}

	}

	public void delete(Long id) {

		repository.deleteById(id);

	}

}
