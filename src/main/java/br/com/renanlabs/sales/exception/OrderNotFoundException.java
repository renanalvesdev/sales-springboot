package br.com.renanlabs.sales.exception;

public class OrderNotFoundException extends RuntimeException{
	
	public OrderNotFoundException() {
		super("Order not found");
	}
	
}
