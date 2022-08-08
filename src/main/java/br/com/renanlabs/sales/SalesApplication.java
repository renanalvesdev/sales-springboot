package br.com.renanlabs.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.renanlabs.sales.domain.entity.Client;
import br.com.renanlabs.sales.domain.repository.Clients;

@SpringBootApplication
public class SalesApplication {
	
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired Clients clients) {
		return args -> {
			Client c = new Client(null, "Michael");
			clients.save(c);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
}
