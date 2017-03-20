package com.project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Product;
import com.project.domain.ProductCategory;
import com.project.repository.GroupRepository;
import com.project.repository.ProductRepository;
import com.project.repository.UserRepository;

@Transactional(readOnly = true)
@Service
public class DefaultProductService implements ProductService{
	
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final GroupRepository groupRepository;
	
	@Autowired
	public DefaultProductService(UserRepository userRepository, ProductRepository productRepository,
			GroupRepository groupRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.groupRepository = groupRepository;
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
	public List<Product> findByProductOwnerUsername(String username) {
		
		return this.productRepository.findByProductOwnerUsername(username);
	}

}
