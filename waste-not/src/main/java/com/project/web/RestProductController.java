package com.project.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.domain.JsonViews;
import com.project.domain.Product;
import com.project.service.ProductService;

@RestController
@RequestMapping("/products")
public class RestProductController {

	private final ProductService productservice;

	@Autowired
	public RestProductController(ProductService productservice) {
		this.productservice = productservice;
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{id}")
	public Product retrieveProductById(@PathVariable Long id) {
		System.err.println(this.productservice.findById(id).getDescription());
		return this.productservice.findById(id);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<Product> listAllProducts() {
		return this.productservice.findAll();
	}	

}
