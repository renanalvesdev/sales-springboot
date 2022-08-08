package br.com.renanlabs.sales.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanlabs.sales.domain.entity.Client;
import br.com.renanlabs.sales.domain.entity.Order;

public interface Orders extends JpaRepository<Order, Integer>{

	List<Order> findByClient(Client client);
	
}
