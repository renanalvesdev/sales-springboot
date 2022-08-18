package br.com.renanlabs.sales.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TB_CLIENT")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name", length = 100)
	@NotEmpty(message = "{field.name.required}")
	private String name;
	
	
	//client doesnt have any key to access orders, 
	//but order has client key. that way we can acees all orders from client through mappedBy
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private Set<Order> orders;
	
	
	public Client(String name) {
		this.name = name;// TODO Auto-generated constructor stub
	}
	
	
	public Client(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}

