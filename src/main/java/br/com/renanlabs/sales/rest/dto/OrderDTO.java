package br.com.renanlabs.sales.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDTO {
	
	@NotNull(message = "Client ID/code is required.")
	private Integer client;
	@NotNull(message = "Total is required.")
	private BigDecimal total;
	private List<ItemOrderDTO> items;
	
}
