package com.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Product;
import com.project.domain.ProductCategory;
import com.project.domain.User;
import com.project.repository.ProductRepository;
import com.project.repository.UserRepository;

@Transactional
@Service
public class DefaultProductService implements ProductService {

	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	@Autowired
	public DefaultProductService(UserRepository userRepository, ProductRepository productRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Product addNewProduct(Product product) {

		return this.productRepository.save(product);
	}

	@Override
	public void deleteById(Long ProductId) {

		this.productRepository.delete(ProductId);
	}

	@Override
	public Product findById(Long ProductId) {

		return this.productRepository.findOne(ProductId);
	}

	@Override
	public List<Product> findAll() {

		return this.productRepository.findAll();
	}

	@Override
	public List<Product> findByProductName(String ProductName) {

		return this.productRepository.findByProductName(ProductName);
	}

	@Override
	public List<Product> findByProductCategory(ProductCategory ProductCategory) {

		return this.productRepository.findByProductCategory(ProductCategory);
	}

	@Override
	public List<Product> findByLocationCity(String City) {

		return this.productRepository.findByLocationCity(City);
	}

	@Override
	public List<Product> findByDatePosted(LocalDate date) {

		return this.productRepository.findByDatePosted(date);
	}

	@Override
	public void handleProductRequest(Long userId, Long productId, String action) {

		Product product = this.productRepository.findOne(productId);
		User userRequested = this.userRepository.findOne(userId);
		User productOwner = this.userRepository.findOne(product.getProductOwner().getId());

		if (userRequested.getProductsRecieved().isEmpty()) {
			List<Product> productsRecievedList = new ArrayList<>();
			userRequested.setProductsRecieved(productsRecievedList);
		}
		if (userRequested.getProductsRequestedByUser().isEmpty()) {
			List<Product> productsRequestedByUserList = new ArrayList<>();
			userRequested.setProductsRequestedByUser(productsRequestedByUserList);
		}
		if (productOwner.getProductsShared().isEmpty()) {
			List<Product> productsSharedList = new ArrayList<>();
			userRequested.setProductsShared(productsSharedList);
		}
		if (productOwner.getProductsRequestedByOthers().isEmpty()) {
			List<Product> productsRecievedList = new ArrayList<>();
			userRequested.setProductsRequestedByOthers(productsRecievedList);
		}
		if (product.getRequestList().isEmpty()) {
			List<User> requestList = new ArrayList<>();
			product.setRequestList(requestList);
		}

		// // When Products are successfully given away - Add To Products Shared
		// List<Product> productsShared
		// // When Products are successfully accepted - Add To Products Received
		// List<Product> productsRecieved
		// List<Product> productsRequestedByUser
		// List<Product> productsRequestedByOthers
		// List<User> requestList
		System.err.println("Action+++++++++++++" + action);

		if (action.contentEquals("Yes")) {
			System.err.println("Yes Action Selected");
			userRequested.getProductsRecieved().add(product);
			userRequested.getProductsRequestedByUser().remove(product);
			productOwner.getProductsShared().add(product);
			productOwner.getProductsRequestedByOthers().remove(product);
			product.getRequestList().remove(userRequested);

		} else if (action.contentEquals("No")) {
			System.err.println("No Selected");
			userRequested.getProductsRequestedByUser().remove(product);
			product.getRequestList().remove(userRequested);
			productOwner.getProductsRequestedByOthers().remove(product);

		} else if (action.contentEquals("Gone")) {
			System.err.println("Gone Action Selected");
			product.setAvailable(false);
			product.getRequestList().remove(userRequested);
			userRequested.getProductsRequestedByUser().remove(product);
			productOwner.getProductsRequestedByOthers().remove(product);
		} else {
			System.err.println("No Action Selected");
		}
	}

}
