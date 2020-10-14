package com.samuelTI.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.samuelTI.api.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;

	public List<CarroDTO> getCarros() {

		return repository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
		/*
		 * List<Carro> carros = repository.findAll(); List<CarroDTO> listDTO =
		 * carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
		 * return listDTO
		 */

	}

	public Optional<CarroDTO> getCarrosById(Long id) {
		return repository.findById(id).map(CarroDTO::create);
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {

		return repository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");
		return CarroDTO.create(repository.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.isNull(id, "Não foi possivel atualizar o registro");

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
			// throw new RuntimeException("Não foi possivel atualizar o registro");
		}

	}

	public boolean delete(Long id) {
		if (getCarrosById(id).isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Carro> getCarrosFakes() {
		List<Carro> carros = new ArrayList<>();

		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(1L, "Brasilia"));
		carros.add(new Carro(1L, "Corovete"));
		carros.add(new Carro(1L, "Chevete"));

		return carros;
	}

}
