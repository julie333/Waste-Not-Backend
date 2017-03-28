package com.project.web;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.domain.Group;
import com.project.domain.JsonViews;
import com.project.domain.Product;
import com.project.domain.User;
import com.project.service.GroupService;
import com.project.service.LocationService;
import com.project.service.ProductService;
import com.project.service.UserService;

@RestController
@RequestMapping("/users")
public class RestUserController {

	private final UserService userservice;
	private final GroupService groupservice;
	private final ProductService productservice;
	private final LocationService locationservice;

	@Autowired
	public RestUserController(UserService userService, GroupService groupservice, ProductService productservice, LocationService locationservice) {
		this.userservice = userService;
		this.groupservice = groupservice;
		this.productservice = productservice;
		this.locationservice = locationservice;
	}

//	createNewProduct
	@PostMapping("/{id:[0-9]+}/createNewProduct")
	public Product createNewProduct(@RequestBody Product postedProduct,@PathVariable Long id) {

		postedProduct.setProductOwner(this.userservice.findById(id));
		Product savedProduct = this.productservice.addNewProduct(postedProduct);
		System.err.println(savedProduct);	
		return savedProduct;
	}
	
	
//	createNewGroup
	@PostMapping("/{id:[0-9]+}/createNewGroup")
	public Group createNewGroup(@RequestBody Group postedGroup,@PathVariable Long id) {
		
		User admin = this.userservice.findById(id);
		postedGroup.setAdmin(admin);
		Group savedGroup = this.groupservice.createGroup(postedGroup);
		this.userservice.joinGroup(admin.getId(), savedGroup.getId());
		System.err.println(savedGroup);
		return savedGroup;
	}
	

//	List of Users
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
	@GetMapping("/{userId}/groups/{groupId}/toggle")
	public void joinEvent( @PathVariable Long userId, @PathVariable Long groupId) {

		User user = userservice.findById(userId);
		Group group = this.groupservice.findById(groupId);
		
		this.userservice.removeFromGroupRequests(userId, groupId);

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

	// Toggle ProductsRequested 
	// ProductId - Products Requested / UserId - User Requesting for product
    // Product Owner should get notification
    // Add to Product Owner's productsRequestedByOthers
    // Add to user's productsRequestedByUser
	
	@GetMapping("/{userId}/productsRequested/{productId}/toggle")
	public void addToProductsRequested(@PathVariable Long productId, @PathVariable Long userId) {

		Product product = this.productservice.findById(productId);
		User userRequestingForProduct = this.userservice.findById(userId);

		if (userRequestingForProduct.getProductsRequestedByUser().contains(product)) {
			this.userservice.deleteProductsRequestedByUser(userId, productId);
		} else {
			this.userservice.addToProductsRequestedByUser(userId, productId);
		}
		
	}
	
	@GetMapping("/grouprequest/add/{userId}/{groupId}")
	public void addToGroupRequests(@PathVariable Long userId, @PathVariable Long groupId) {
		this.userservice.addToGroupRequests(userId, groupId);
	}
	
	@GetMapping("/grouprequest/remove/{userId}/{groupId}")
	public void removeGroupRequests(@PathVariable Long userId, @PathVariable Long groupId) {
		this.userservice.removeFromGroupRequests(userId, groupId);
	}

	// Delete User
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable Long id) {

		this.userservice.deleteById(id);
		System.err.println("deleteUser");
	}

	// Delete User Product
	@DeleteMapping("/delete/{id}/{productId}")
	public void deleteUserProduct(@PathVariable Long id, @PathVariable Long productId) {
		System.err.println("deleteUserProduct");
		this.userservice.deleteProductsPosted(id, productId);
	}

}
