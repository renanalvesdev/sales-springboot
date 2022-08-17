package br.com.renanlabs.sales.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.renanlabs.sales.domain.entity.Client;
import br.com.renanlabs.sales.domain.entity.ItemOrder;
import br.com.renanlabs.sales.domain.entity.Order;
import br.com.renanlabs.sales.domain.entity.Product;
import br.com.renanlabs.sales.domain.enums.OrderStatus;
import br.com.renanlabs.sales.domain.repository.Clients;
import br.com.renanlabs.sales.domain.repository.ItensOrder;
import br.com.renanlabs.sales.domain.repository.Orders;
import br.com.renanlabs.sales.domain.repository.Products;
import br.com.renanlabs.sales.exception.BusinessException;
import br.com.renanlabs.sales.exception.OrderNotFoundException;
import br.com.renanlabs.sales.rest.dto.ItemOrderDTO;
import br.com.renanlabs.sales.rest.dto.OrderDTO;
import br.com.renanlabs.sales.service.OrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final Orders repository;
	private final ItensOrder itensOrderRepository;
	private final Clients clientRepository;
	private final Products productRepository;

	@Override
	@Transactional
	public Order save(OrderDTO dto) {

		Integer clientId = dto.getClient();
		
		Client client = clientRepository
				.findById(clientId)
				.orElseThrow(() -> new BusinessException("Client doesnt exists"));

		Order order = new Order();
		order.setTotal(dto.getTotal());
		order.setDateOrder(LocalDate.now());
		order.setClient(client);
		order.setStatus(OrderStatus.FINISHED);
		
		List<ItemOrder> itensOrder = convertDtoItensOrderToItemOrder(order, dto.getItems());
		repository.save(order);
		itensOrderRepository.saveAll(itensOrder);
		order.setItens(itensOrder);
		
		return order;
	}

	public List<ItemOrder> convertDtoItensOrderToItemOrder(Order order, List<ItemOrderDTO> itensOrderDto) {

		return itensOrderDto
				.stream()
				.map(item -> {
					Integer productId = item.getProduct();
					Product productItem = productRepository
							.findById(productId)
							.orElseThrow(() -> new BusinessException("Product doesnt exists"));
	
					ItemOrder itemOrder = new ItemOrder();
					itemOrder.setAmount(item.getAmount());
					itemOrder.setOrder(order);
					itemOrder.setProduct(productItem);
					
					return itemOrder;
				
		}).collect(Collectors.toList());
	}

	
	@Override
	public Optional<Order> getCompleteOrder(Integer id) {
		return repository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void updateStatus(Integer id, OrderStatus orderStatus) {
		//recuperar pedido do banco
		 repository
				 .findById(id)
				 .map(order -> {
					 order.setStatus(orderStatus);
					 return repository.save(order);
				 })
				 .orElseThrow(() -> new OrderNotFoundException());
		
	}

}
