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
	
 
	@PostMapping("/searchedproducts")
	public List<Product> searchedproducts(@RequestBody String searchedProduct) {
		
		
		String searchItem = searchedProduct.substring(searchedProduct.indexOf(":")+2,
													 searchedProduct.indexOf("}")-1);
		System.err.println(searchItem);
		
		List<Product> products= new ArrayList<>();
		List<Product> searchProductsList = new ArrayList<>();


		products = this.productservice.findByProductName(searchItem);
			
		if (products.isEmpty()) {
			return searchProductsList;
		}
		else{
			searchProductsList.addAll(products);
			return searchProductsList;
		}
		
	}
	
	@PostMapping("/searchedusers")
	public List<User> searchedusers(@RequestBody String searchedUser) {
			
		List<User> users= new ArrayList<>();
		List<User> searchUsersList = new ArrayList<>();

		System.err.println(searchedUser);
		
		String searchItem = searchedUser.substring(1,searchedUser.length()-1);
		
		System.err.println("HEREEEEEEEEE" + searchItem);

		users = this.userservice.findUsers(searchItem);
			
		if (users.isEmpty()) {
			return searchUsersList;
		}
		else{
			searchUsersList.addAll(users);
			System.err.println(searchUsersList);
			return searchUsersList;
		}
		
	}

	@JsonView(JsonViews.Public.class)
	@GetMapping("/quotes")
	public String fetchRandomnQuote() {
		
		List<String> quotes = new ArrayList<>();
				
		quotes.add("Destroying rainforest for economic gain is like burning a Renaissance painting to cook a meal. - E. O. Wilson");
		quotes.add("By polluting clear water with slime you will never find good drinking water. - Aeschylus");	 
		quotes.add("It makes a big difference to recycle. It makes a big difference to use recycled products. It makes a big difference to reuse things, to not use the paper cup -"
				+ " and each time you do, that's a victory. Emily Deschanel");
		quotes.add("Because the living environment is what really sustains us. - E. O. Wilson");	 
		

		
		int randomn = (int) (Math.floor(Math.random())*4);
		System.err.println("cdfvgbhjnmk");
		return quotes.get(randomn);

	}
	
}
