package br.com.renanlabs.sales.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemOrderDTO {

	private Integer product;
	private Integer amount;
}
