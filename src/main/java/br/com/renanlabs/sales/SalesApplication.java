package br.com.renanlabs.sales;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.renanlabs.sales.domain.entity.Client;
import br.com.renanlabs.sales.domain.entity.Order;
import br.com.renanlabs.sales.domain.repository.Clients;
import br.com.renanlabs.sales.domain.repository.Orders;

@SpringBootApplication
public class SalesApplication {
	
	@Value("${application.name}")
	private String applicationName;

	
	@Bean
	public CommandLineRunner init(
			@Autowired Clients clients,
			@Autowired Orders orders
	) 
	
	{
		return args -> {
			
			System.out.println("Saving clients");
			Client michael = new Client("Michael");
			clients.save(michael);
			
			Order o = new Order();
			o.setClient(michael);
			o.setDateOrder(LocalDate.now());
			o.setTotal(BigDecimal.valueOf(100));
			
			orders.save(o);
			
			System.out.println(michael);
			
			Client client = clients.findClientFetchOrders(michael.getId());
			System.out.println(client.getOrders());
			
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
}
