package br.com.renanlabs.sales.rest.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.renanlabs.sales.domain.entity.Client;
import br.com.renanlabs.sales.domain.repository.Clients;

@Controller
public class ClientController {

	private Clients clients;
	
	public ClientController(Clients clients) {
		super();
		this.clients = clients;
	}

	@GetMapping("/api/clients/{id}")
	@ResponseBody
	//variable via URL in path
	public ResponseEntity<Client> findClientById(@PathVariable Integer id) {
		Optional<Client> client = clients.findById(id);
		
		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/api/clients/{id}")
	@ResponseBody
	public ResponseEntity save (@RequestBody Client client) {
		Client savedClient = clients.save(client);
		return ResponseEntity.ok(savedClient);
	}
	
	@DeleteMapping("/api/clientes/{id}")
	public ResponseEntity delete(@PathVariable Integer id) {	
		
		Optional<Client> client = clients.findById(id);
		
		if(client.isPresent()) {
			clients.delete(client.get());
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
}
