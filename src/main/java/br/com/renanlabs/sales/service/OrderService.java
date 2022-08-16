package br.com.renanlabs.sales.service;

import java.util.Optional;

import br.com.renanlabs.sales.domain.entity.Order;
import br.com.renanlabs.sales.rest.dto.OrderDTO;

public interface OrderService {
	
	Order save(OrderDTO dto);
	
	Optional<Order> getCompleteOrder(Integer id);
	
}
