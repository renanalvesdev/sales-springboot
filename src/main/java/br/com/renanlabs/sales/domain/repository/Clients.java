package br.com.renanlabs.sales.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.renanlabs.sales.domain.entity.Client;

public interface Clients extends JpaRepository<Client, Integer>{

	List<Client> findByNameLike(String name);
	
	
	@Query(" select c from Client c left join fetch c.orders p where c.id = :id ")
	Client findClientFetchOrders(@Param("id") Integer id);
	  
}
