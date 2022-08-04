package br.com.renanlabs.sales.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TB_ORDER")
public class Order {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Column(name = "date_order")
	private LocalDate dateOrder;
	
	
	@Column(name = "total", precision = 20, scale = 2 )
	private BigDecimal total;
	
	
	@OneToMany(mappedBy = "order")
	private List<ItemOrder> itens;
	
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
	public List<ItemOrder> getItens() {
		return itens;
	}
	public void setItens(List<ItemOrder> itens) {
		this.itens = itens;
	}
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", dateOrder=" + dateOrder + "]";
	}
	
	
	
}
