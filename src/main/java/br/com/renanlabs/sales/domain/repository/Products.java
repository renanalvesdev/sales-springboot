package br.com.renanlabs.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanlabs.sales.domain.entity.Product;

public interface Products extends JpaRepository<Product, Integer>{

	
	
}
