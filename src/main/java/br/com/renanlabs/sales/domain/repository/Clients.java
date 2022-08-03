package br.com.renanlabs.sales.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanlabs.sales.domain.entity.Client;

public interface Clients extends JpaRepository<Client, Integer>{

	List<Client> findByNameLike(String name);
	
}
