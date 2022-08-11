package br.com.renanlabs.sales.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDTO {
	private Integer client;
	private BigDecimal total;
	private List<ItemOrderDTO> items;
	
}
