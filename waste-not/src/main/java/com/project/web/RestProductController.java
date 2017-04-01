package com.project.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.domain.JsonViews;
import com.project.domain.Product;
import com.project.service.ProductService;
import com.project.service.UserService;

@RestController
@RequestMapping("/products")
public class RestProductController {

	private final ProductService productservice;
	private final UserService userservice;

	@Autowired
	public RestProductController(ProductService productservice, UserService userservice) {
		super();
		this.productservice = productservice;
		this.userservice = userservice;
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

	@PostMapping("/{userId}/requestAction/{productId}")
	public void searchedproducts(@PathVariable Long userId, @PathVariable Long productId,
			@RequestBody String selected) {

		String action = selected.substring(1, selected.length() - 1);

		System.err.println(action + selected);

		this.productservice.handleProductRequest(userId, productId, action);

	}
}
