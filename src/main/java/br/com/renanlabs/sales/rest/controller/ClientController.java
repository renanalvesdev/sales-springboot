package br.com.renanlabs.sales.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.renanlabs.sales.domain.entity.Client;
import br.com.renanlabs.sales.domain.repository.Clients;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private Clients clients;
	
	public ClientController(Clients clients) {
		super();
		this.clients = clients;
	}

	@GetMapping("{id}")
	//variable via URL in path
	public Client getClientById(@PathVariable Integer id) {
		
		return clients
				.findById(id)
				.orElseThrow(() -> 
						new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Client not found"));
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save (@RequestBody @Valid Client client) {		
		return clients.save(client);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {	
		
		clients
			.findById(id)
			.map(client -> {
				clients.delete(client);
				return client;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Client not found"));
	}
	
	@PutMapping("{id}")
	public void update(@PathVariable Integer id,
								 @RequestBody @Valid Client client) {
			clients
				.findById(id)
				.map(foundClient -> {
					client.setId(foundClient.getId());
					clients.save(client);
					return foundClient;
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Client not found"));
	}
	
	@GetMapping
	public List<Client> find (Client filter) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(
											ExampleMatcher.StringMatcher.CONTAINING );
	
		Example example = Example.of(filter, matcher);
		return clients.findAll(example);
	}
}
