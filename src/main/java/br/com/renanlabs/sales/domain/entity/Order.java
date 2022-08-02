package br.com.renanlabs.sales.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {

	
	private Integer id;
	private Client client;
	private LocalDate dateOrder;
	private BigDecimal total;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public LocalDate getDateOrder() {
		return dateOrder;
	}
	public void setDateOrder(LocalDate dateOrder) {
		this.dateOrder = dateOrder;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
}
