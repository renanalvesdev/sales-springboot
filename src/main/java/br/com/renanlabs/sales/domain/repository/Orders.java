package br.com.renanlabs.sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renanlabs.sales.domain.entity.Order;

public interface Orders extends JpaRepository<Order, Integer>{

}
