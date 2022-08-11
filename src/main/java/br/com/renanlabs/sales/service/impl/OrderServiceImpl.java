package br.com.renanlabs.sales.service.impl;

import org.springframework.stereotype.Service;

import br.com.renanlabs.sales.domain.repository.Orders;
import br.com.renanlabs.sales.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private Orders repository;

	public OrderServiceImpl(Orders repository) {
		this.repository = repository;
	}
	
	
}
