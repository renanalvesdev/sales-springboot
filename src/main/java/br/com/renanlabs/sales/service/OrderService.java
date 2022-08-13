package br.com.renanlabs.sales.service;

import br.com.renanlabs.sales.domain.entity.Order;
import br.com.renanlabs.sales.rest.dto.OrderDTO;

public interface OrderService {
	
	Order save(OrderDTO dto);
	
}
