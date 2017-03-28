package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Group;
import com.project.domain.Product;
import com.project.domain.User;
import com.project.repository.GroupRepository;
import com.project.repository.ProductRepository;
import com.project.repository.UserRepository;

@Transactional
@Service
public class DefaultUserService implements UserService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final GroupRepository groupRepository;

	@Autowired
	public DefaultUserService(UserRepository userRepository, ProductRepository productRepository,
			GroupRepository groupRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.groupRepository = groupRepository;
	}

	@Override
	public User registerNewUser(User user) {

		return this.userRepository.save(user);
	}

	@Override
	public User findById(Long id) {

		return this.userRepository.findOne(id);
	}

	@Override
	public void deleteById(Long userId) {

		// Delete Product if Owner is deleted

		User user = this.userRepository.findOne(userId);

		List<Product> products = this.productRepository.findAll();
		for (Product product : products) {
			if (product.getProductOwner().equals(user))
				this.productRepository.delete(product);
		}

		// Delete Group if Admin is deleted

		List<Group> groups = this.groupRepository.findAll();
		for (Group group : groups) {
			if (group.getAdmin().equals(user))
				this.groupRepository.delete(group);
		}

		this.userRepository.delete(userId);
	}

	@Override
	public List<User> findAll() {

		return this.userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {

		return this.userRepository.findByUsername(username);
	}

	@Override
	public List<Product> findAllProductsOfUserById(Long userId) {

		return this.userRepository.findOne(userId).getProductsPosted();
	}

	//
	@Override
	public void joinGroup(Long userId, Long groupId) {

		User user = userRepository.findOne(userId);
		Group group = groupRepository.findOne(groupId);

		// Add To user Groups
		if (user.getGroups().isEmpty()) {
			List<Group> groupsList = new ArrayList<>();
			user.setGroups(groupsList);
			user.getGroups().add(group);

		} else {
			user.getGroups().add(group);
		}

		//// Add To Group members
		// if (group.getMembers().size()<1) {
		// List<User> userList = new ArrayList<>();
		// group.setMembers(userList);
		// group.getMembers().add(user);
		//
		// } else {
		// group.getMembers().add(user);
		//
		// }

	}

	@Override
	public void leaveGroup(Long userId, Long groupId) {

		User user = userRepository.findOne(userId);
		Group group = groupRepository.findOne(groupId);

		if (user.getGroups().isEmpty()) {

		} else {
			user.getGroups().remove(group);
		}

		if (group.getMembers().isEmpty()) {

		} else {
			group.getMembers().remove(user);
		}

	}

	@Override
	public void addFriend(Long userId, Long friendId) {

		User user = userRepository.findOne(userId);
		User friend = userRepository.findOne(friendId);

		if (user.getFriends().isEmpty()) {
			List<User> friendsList = new ArrayList<>();
			user.setFriends(friendsList);
			user.getFriends().add(friend);

		} else {
			user.getFriends().add(friend);
		}

	}

	@Override
	public void removeFriend(Long userId, Long friendId) {

		User user = userRepository.findOne(userId);
		User friend = userRepository.findOne(friendId);

		if (user.getFriends().isEmpty()) {

		} else {
			user.getFriends().remove(friend);
		}

	}

	@Override
	public void addToWishList(Long userId, String wish) {

		User user = userRepository.findOne(userId);

		if (user.getWishList().isEmpty()) {
			List<String> wishList = new ArrayList<>();
			user.setWishList(wishList);
			user.getWishList().add(wish);

		} else {
			user.getWishList().add(wish);
		}

	}

	@Override
	public void editWishList(Long userId, String wish) {

		User user = userRepository.findOne(userId);

		if (user.getWishList().isEmpty()) {
			List<String> wishList = new ArrayList<>();
			user.setWishList(wishList);
			user.getWishList().remove(wish);

		} else {
			user.getWishList().remove(wish);
		}

	}

	@Override
	public void addToProductsPosted(Long userId, Long productId) {

		User user = userRepository.findOne(userId);
		Product product = productRepository.findOne(productId);

		if (user.getProductsPosted().isEmpty()) {
			List<Product> productsPostedList = new ArrayList<>();
			user.setProductsPosted(productsPostedList);
			user.getProductsPosted().add(product);

		} else {
			user.getProductsPosted().add(product);
		}

	}

	@Override
	public void deleteProductsPosted(Long userId, Long productId) {

		User user = this.userRepository.findOne(userId);
		Product product = this.productRepository.findOne(productId);

		// Delete Product from Products Table

		this.productRepository.delete(product);

		if (user.getProductsPosted().isEmpty()) {

		} else {
			user.getProductsPosted().remove(product);
		}

	}

	@Override
	public void addToProductsRequestedByUser(Long userId, Long productId) {

		Product product = productRepository.findOne(productId);
		User userRequestingForProduct = userRepository.findOne(userId);
		User productOwner = userRepository.findOne(product.getProductOwner().getId());

		// Add To Requested Users ProductsRequestedByUser List

		if (userRequestingForProduct.getProductsRequestedByUser().isEmpty()) {
			List<Product> productsRequestedByUserList = new ArrayList<>();
			userRequestingForProduct.setProductsRequestedByUser(productsRequestedByUserList);
			userRequestingForProduct.getProductsRequestedByUser().add(product);

		} else {
			userRequestingForProduct.getProductsRequestedByUser().add(product);
		}

		product.getRequestList().add(userRequestingForProduct.getId());

		// Add To Product Owners ProductsRequestedByOthers List
		if (productOwner.getProductsRequestedByOthers().isEmpty()) {
			List<Product> productsRequestedByOthersList = new ArrayList<>();
			productOwner.setProductsRequestedByOthers(productsRequestedByOthersList);
			productOwner.getProductsRequestedByOthers().add(product);

		} else {
			productOwner.getProductsRequestedByOthers().add(product);
		}

	}

	@Override
	public void deleteProductsRequestedByUser(Long userId, Long productId) {

		Product product = productRepository.findOne(productId);
		User userRequestingForProduct = userRepository.findOne(userId);
		User productOwner = userRepository.findOne(product.getProductOwner().getId());

		if (userRequestingForProduct.getProductsRequestedByUser().isEmpty()) {

		} else {
			userRequestingForProduct.getProductsRequestedByUser().remove(product);
		}

		product.getRequestList().remove(userRequestingForProduct.getId());

		if (productOwner.getProductsRequestedByOthers().isEmpty()) {

		} else {
			productOwner.getProductsRequestedByOthers().remove(product);
		}

	}

	// Just Displays - No Scope for void references - Delete From Products
	// Posted

	@Override
	public void addToProductsShared(Long userId, Long productId) {

		User user = userRepository.findOne(userId);
		Product product = productRepository.findOne(productId);

		// Delete Product from Products Table - Not sure if required
		// this.productRepository.delete(product);

		// Delete From Products Posted
		if (user.getProductsPosted().isEmpty()) {
		} else {
			user.getProductsPosted().remove(product);
		}

		if (user.getProductsShared().isEmpty()) {
			List<Product> productsSharedList = new ArrayList<>();
			user.setProductsShared(productsSharedList);
			user.getProductsShared().add(product);

		} else {
			user.getProductsShared().add(product);
		}
	}

	@Override
	public void addToProductsRecieved(Long userId, Long productId) {

		User user = userRepository.findOne(userId);
		Product product = productRepository.findOne(productId);

		if (user.getProductsRecieved().isEmpty()) {
			List<Product> productsRecievedList = new ArrayList<>();
			user.setProductsRecieved(productsRecievedList);
			user.getProductsRecieved().add(product);

		} else {
			user.getProductsRecieved().add(product);
		}
	}

	@Override
	public List<User> findUsers(String userName) {
		return this.userRepository.findUsers(userName);
	}

	@Override
	public void addToGroupRequests(Long userId, Long groupId) {

		User user = this.userRepository.findOne(userId);
		Group group = this.groupRepository.findOne(groupId);

		if (user.getGroupRequests().isEmpty()) {
			List<Group> groupRequests = new ArrayList<>();
			user.setGroupRequests(groupRequests);
			user.getGroupRequests().add(group);

		} else {
			user.getGroupRequests().add(group);
		}
		System.err.println(user.getGroupRequests());

	}

	@Override
	public void removeFromGroupRequests(Long userId, Long groupId) {
		
		User user = this.userRepository.findOne(userId);
		Group group = this.groupRepository.findOne(groupId);
		
		if(user.getGroupRequests().isEmpty()) {

		} else {
			user.getGroupRequests().remove(group);
		}
	}
	


}
