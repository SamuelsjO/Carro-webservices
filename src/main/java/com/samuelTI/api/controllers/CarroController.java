package com.samuelTI.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.PageRequest;

import com.samuelTI.api.domain.Carro;
import com.samuelTI.api.domain.CarroService;
import com.samuelTI.api.domain.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarroService service;

	@GetMapping
	public ResponseEntity<?> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
								 @RequestParam(value = "size", defaultValue = "10") Integer size) {
		 List<CarroDTO> carros = service.getCarros(PageRequest.of(page, size));
	     return ResponseEntity.ok(carros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		CarroDTO carro = service.getCarrosById(id);

		return ResponseEntity.ok(carro);
	
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<?> getCarrosByTipo(@PathVariable("tipo") String tipo,
											 @RequestParam(value = "page", defaultValue = "0") Integer page,
										     @RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<CarroDTO> carros = service.getCarrosByTipo(tipo, PageRequest.of(page, size));

		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam("query") String query) {
	    List<CarroDTO> carros = service.search(query);
	    return carros.isEmpty() ?
	            ResponseEntity.noContent().build() :
	            ResponseEntity.ok(carros);
	}

	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<?> post(@RequestBody Carro carro) {

			CarroDTO c = service.insert(carro);
			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody Carro carro) {

		carro.setId(id);
		CarroDTO c = service.update(carro, id);

		return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
