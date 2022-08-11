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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
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
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", dateOrder=" + dateOrder + "]";
	}
	
	
	
}
