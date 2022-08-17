package br.com.renanlabs.sales.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.renanlabs.sales.domain.entity.ItemOrder;
import br.com.renanlabs.sales.domain.entity.Order;
import br.com.renanlabs.sales.domain.enums.OrderStatus;
import br.com.renanlabs.sales.rest.dto.OrderDTO;
import br.com.renanlabs.sales.rest.dto.OrderDetailsDTO;
import br.com.renanlabs.sales.rest.dto.OrderItemInfoDTO;
import br.com.renanlabs.sales.rest.dto.OrderStatusUpdateDTO;
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
	public Integer save(@RequestBody @Valid OrderDTO dto) {
		Order order = service.save(dto);
		return order.getId();
	}
	
	
	@GetMapping("{id}")
	public OrderDetailsDTO getById(@PathVariable Integer id) {
		return service
				.getCompleteOrder(id)
				.map(o -> converter(o))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable Integer id,
							@RequestBody OrderStatusUpdateDTO dto) {
		
		String newStatus = dto.getNewStatus();
		service.updateStatus(id, OrderStatus.valueOf(newStatus));
	}
	
	private OrderDetailsDTO converter(Order order) {
		return OrderDetailsDTO
			.builder()
			.id(order.getId())
			.dateOrder(order.getDateOrder().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
			.clientName(order.getClient().getName())
			.total(order.getTotal())
			.status(order.getStatus().name())
			.items(converter(order.getItens()))
			.build();
	}
	
	
	private List<OrderItemInfoDTO> converter(List<ItemOrder> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(
				item -> OrderItemInfoDTO
				.builder().productDescription(item.getProduct().getDescription())
				.unitPrice(item.getProduct().getPrice())
				.amount(item.getAmount())
				.build()
		).collect(Collectors.toList());
	}
}
