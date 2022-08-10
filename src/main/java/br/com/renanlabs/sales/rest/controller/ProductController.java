package br.com.renanlabs.sales.rest.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.renanlabs.sales.domain.entity.Product;
import br.com.renanlabs.sales.domain.repository.Products;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

	private Products products;
	
	public ProductController(Products products) {
		this.products = products;
	}
	
	@GetMapping("{id}")
	public Product findById(@PathVariable Integer id) {
		return products
				.findById(id)
				.orElseThrow(() -> 
							new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
						);
	}
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product save(@RequestBody Product product) {
		return products.save(product);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		
		products
			.findById(id)
			.map(found -> {
				products.delete(found);
				return found;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}
	
	@PutMapping("{id}")
	public void update(@PathVariable Integer id, @RequestBody Product product) {
		products
			.findById(id)
			.map(foundProduct -> {
				product.setId(id);
				products.save(product);
				return foundProduct;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}

	@GetMapping
	public List<Product> find (Product filter) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(
											ExampleMatcher.StringMatcher.CONTAINING );
	
		Example example = Example.of(filter, matcher);
		return products.findAll(example);
	}
	
}
