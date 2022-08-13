package br.com.renanlabs.sales.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanlabs.sales.domain.entity.Order;
import br.com.renanlabs.sales.rest.dto.OrderDTO;
import br.com.renanlabs.sales.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private OrderService service;

	public OrderController(OrderService service) {
		this.service = service;
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody OrderDTO dto) {
		Order order = service.save(dto);
		return order.getId();
	}
	
}
