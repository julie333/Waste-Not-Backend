package com.project.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.ArrayList;
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
import com.project.domain.User;
import com.project.service.GroupService;
import com.project.service.ProductService;
import com.project.service.UserService;

@RestController
@RequestMapping("/")
public class RestWebsiteController {

	private final UserService userservice;
	private final GroupService groupservice;
	private final ProductService productservice;

	@Autowired
	public RestWebsiteController(UserService userService, GroupService groupservice, ProductService productservice) {
		this.userservice = userService;
		this.groupservice = groupservice;
		this.productservice = productservice;
	}
	
	@JsonView(JsonViews.NewUser.class)
	@PostMapping("/register")
	public HttpEntity<Void> registerNewUser(@RequestBody User postedUser) {
		User savedUser = userservice.registerNewUser(postedUser);

		UriComponents uriComponents = fromMethodCall(
			on(getClass()).retrieveUserByUsername(savedUser.getUsername())).build();

		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}
	
	@JsonView(JsonViews.Public.class)
	@GetMapping("/{username}")
	public User retrieveUserByUsername(@PathVariable String username) {
		return userservice.findByUsername(username);
	}

	@JsonView(JsonViews.NewUser.class)
	@PostMapping("/login")
	public User login(@RequestBody User loginUser) {
		System.err.println(loginUser);

		User user = this.userservice.findByUsername(loginUser.getUsername());

		if (user != null) {
			if (user.getPassword().equals(loginUser.getPassword()))
				return user;
			else
				return loginUser;
		}
		else return loginUser;
	}
	
	@JsonView(JsonViews.NewUser.class)	 
	@PostMapping("/searchedproducts")
	public List<Product> searchedproducts(@RequestBody Product searchedProduct) {
		
		System.err.println(searchedProduct);
		
		List<Product> products= new ArrayList<>();
		List<Product> searchProductsList = new ArrayList<>();

		products = this.productservice.findByProductName(searchedProduct.getProductName());
			
		if (products.isEmpty()) {
			return searchProductsList;
		}
		else{
			searchProductsList.addAll(products);
			return searchProductsList;
		}
		
	}
	
}
