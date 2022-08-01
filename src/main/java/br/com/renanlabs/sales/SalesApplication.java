package br.com.renanlabs.sales;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SalesApplication {
	
	@Value("${application.name}")
	private String applicationName;

	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
}
