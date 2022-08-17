package br.com.renanlabs.sales.service;

import java.util.Optional;

import br.com.renanlabs.sales.domain.entity.Order;
import br.com.renanlabs.sales.domain.enums.OrderStatus;
import br.com.renanlabs.sales.rest.dto.OrderDTO;
import ch.qos.logback.core.util.StatusPrinter;

public interface OrderService {
	
	Order save(OrderDTO dto);
	
	Optional<Order> getCompleteOrder(Integer id);
	
	void updateStatus(Integer id, OrderStatus orderStatus);
	
}
