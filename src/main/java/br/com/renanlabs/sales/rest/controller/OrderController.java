package br.com.renanlabs.sales.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanlabs.sales.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}
	
	
}
