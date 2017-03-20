package com.project.service;

import java.time.LocalDate;
import java.util.List;

import com.project.domain.Product;
import com.project.domain.ProductCategory;

public interface ProductService {
		
		public Product addNewProduct(Product Product);
		
		public void deleteById(Long ProductId);

		public Product findById(Long ProductId);
		
		public List<Product> findAll();
			
		public List<Product> findByProductName(String ProductName);
		
		public List<Product> findByProductCategory(ProductCategory ProductCategory);
		
		public List<Product> findByLocationCity(String City);
		
		public List<Product> findByDatePosted(LocalDate date);
		
		public List<Product> findByProductOwnerUsername(String username);

	}




