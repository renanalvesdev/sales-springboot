package br.com.renanlabs.sales.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.renanlabs.sales.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDTO {
	
	@NotNull(message = "{field.client-code.required}")
	private Integer client;
	@NotNull(message = "{field.total-order.required}")
	private BigDecimal total;
	
	@NotEmptyList(message = "{field.items-order.required}")
	private List<ItemOrderDTO> items;
	
}
