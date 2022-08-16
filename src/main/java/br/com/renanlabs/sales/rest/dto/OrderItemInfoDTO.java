package br.com.renanlabs.sales.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemInfoDTO {

	private String productDescription;
	private BigDecimal unitPrice;
	private Integer amount;
	
}
