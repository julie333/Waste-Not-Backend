
package com.project.service;

import java.util.List;

import com.project.domain.Product;
import com.project.domain.User;


public interface UserService {

	public User registerNewUser(User user);
	
	public User findById(Long id);
	
	public void deleteById(Long userId);
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
	public List<User> findUsers(String userName);

	public List<Product> findAllProductsOfUserById(Long userId);
		
	public void joinGroup(Long userId,Long groupId);
	
	public void leaveGroup(Long userId,Long groupId);
	
	public void addFriend(Long userId,Long friendId);
	
	public void removeFriend(Long userId,Long friendId);
	
	public void addToWishList(Long userId, String wish);
	
	public void editWishList(Long userId, String wish);
	
	public void addToProductsPosted(Long userId, Long productId);
	
	public void deleteProductsPosted(Long userId, Long productId);
	
	public void addToProductsShared(Long userId, Long productId);
	
	public void addToProductsRecieved(Long userId, Long productId);
	
	public void addToProductsRequestedByUser(Long userId, Long productId);
	
	public void deleteProductsRequestedByUser(Long userId, Long productId);
	
	public void addToGroupRequests(Long userId, Long groupId);
	
	public void removeFromGroupRequests(Long userId, Long groupId);
	
}
