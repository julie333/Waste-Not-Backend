package com.project.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

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
	
	@JsonView(JsonViews.NewProduct.class)
	@PostMapping
	public HttpEntity<Void> createNewProduct(@RequestBody Product postedProduct) {

		Product savedProduct = this.productservice.addNewProduct(postedProduct);

		UriComponents uriComponents = fromMethodCall(on(getClass()).retrieveProductById(savedProduct.getId())).build();

		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}
	
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{id}")
	public Product retrieveProductById(@PathVariable Long id) {
		System.out.println(this.productservice.findById(id).getDescription());
		return this.productservice.findById(id);
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping
	public List<Product> listAllProducts() {
		return this.productservice.findAll();
	}	

}
