package br.com.renanlabs.sales.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsDTO {
	
	private Integer id;
	private String clientName;
	private BigDecimal total;
	private String dateOrder;
	private String status;
	private List<OrderItemInfoDTO> items;
	
}
