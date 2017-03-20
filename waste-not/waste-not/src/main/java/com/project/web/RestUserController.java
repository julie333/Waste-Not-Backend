package com.project.web;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.domain.Group;
import com.project.domain.JsonViews;
import com.project.domain.Product;
import com.project.domain.User;
import com.project.service.GroupService;
import com.project.service.ProductService;
import com.project.service.UserService;

@RestController
@RequestMapping("/users")
public class RestUserController {

	private final UserService userservice;
	private final GroupService groupservice;
	private final ProductService productservice;

	@Autowired
	public RestUserController(UserService userService, GroupService groupservice, ProductService productservice) {
		this.userservice = userService;
		this.groupservice = groupservice;
		this.productservice = productservice;
	}

	@JsonView(JsonViews.NewUser.class)
	@PostMapping
	public HttpEntity<Void> registerNewUser(@RequestBody User postedUser) {
		User savedUser = this.userservice.registerNewUser(postedUser);

		UriComponents uriComponents = fromMethodCall(on(getClass()).retrieveUserById(savedUser.getId())).build();

		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}

	@JsonView(JsonViews.NewGroup.class)
	@PostMapping("/{id}/createNewGroup")
	public HttpEntity<Void> createNewGroup(@RequestBody Group postedGroup) {
		Group savedGroup = this.groupservice.createGroup(postedGroup);

		UriComponents uriComponents = fromMethodCall(on(getClass()).retrieveUserById(savedGroup.getId())).build();

		return ResponseEntity.created(uriComponents.encode().toUri()).build();
	}

	@GetMapping
	@JsonView(JsonViews.Public.class)
	public List<User> listAllUsers() {
		return this.userservice.findAll();
	}

	// UserID
	@GetMapping("/{id:[0-9]+}")
	@JsonView(JsonViews.Public.class)
	public User retrieveUserById(@PathVariable Long id) {
		return this.userservice.findById(id);
	}

	// Products
	@GetMapping("/{id}/products")
	@JsonView(JsonViews.Public.class)
	public List<Product> listAllProductsByUser(@PathVariable Long id) {
		return this.userservice.findAllProductsOfUserById(id);
	}

	// Username
	@GetMapping("/{username:[a-zA-Z]+}")
	@JsonView(JsonViews.Public.class)
	public User listAllUsersByUserName(@PathVariable String username) {
		return this.userservice.findByUsername(username);
	}

	// Groups
	@GetMapping("/{id}/groups")
	@JsonView(JsonViews.Public.class)
	public List<Group> listAllGroupsByUser(@PathVariable Long id) {
		return this.userservice.findById(id).getGroups();
	}

	// Join/Leave Group
	@PostMapping("/{id}/groups/{groupId}/toggle")
	public void joinEvent(@PathVariable Long groupId, @RequestBody Long userId) {

		User user = userservice.findById(userId);
		Group group = this.groupservice.findById(groupId);

		if (user.getGroups().contains(group)) {
			this.userservice.leaveGroup(userId, groupId);
		} else {
			this.userservice.joinGroup(userId, groupId);
		}
	}

	// Add/Remove Friend
	@PostMapping("/{id}/friends/{friendId}/toggle")
	public void addFriend(@PathVariable Long friendId, @RequestBody Long userId) {

		User friend = userservice.findById(friendId);
		User user = userservice.findById(userId);

		if (user.getFriends().contains(friend)) {
			this.userservice.removeFriend(userId, friendId);
		} else {
			this.userservice.addFriend(userId, friendId);
		}
	}

	// Add/Remove ProductsPosted -- Process is create a new Product and then add
	// to products posted
	@PostMapping("/{id}/products/productsPosted/{productId}/toggle")
	public void addToProductsPosted(@PathVariable Long productId, @RequestBody Long userId) {

		Product product = this.productservice.findById(productId);
		User user = userservice.findById(userId);

		if (user.getProductsPosted().contains(product)) {
			this.userservice.deleteProductsPosted(userId, productId);
		} else {
			this.userservice.addToProductsPosted(userId, productId);
		}
	}

	// Add/Remove ProductsRequested
	@PostMapping("/{id}/products/productsRequested/{productId}/toggle")
	public void addToProductsRequestedByUser(@PathVariable Long productId, @RequestBody Long userId) {

		Product product = this.productservice.findById(productId);
		User user = userservice.findById(userId);

		if (user.getProductsRequestedByUser().contains(product)) {
			this.userservice.deleteProductsRequestedByUser(userId, productId);
		} else {
			this.userservice.addToProductsRequestedByUser(userId, productId);
		}
	}

	// Delete User
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable Long id) {

		System.err.println("deleteUser");
		this.userservice.deleteById(id);
	}

	// Delete User Product
	@DeleteMapping("/delete/{id}/{productId}")
	public void deleteUserProduct(@PathVariable Long id, @PathVariable Long productId) {
		System.err.println("deleteUserProduct");
		this.userservice.deleteProductsPosted(id, productId);
	}

}
