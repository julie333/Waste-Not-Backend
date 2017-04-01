package com.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Product;
import com.project.domain.ProductCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("from Product p where lower(p.productName) like %?1% or lower(p.productCategory) like %?1% and p.available is true")
	public List<Product> findByProductName(String productName);
	
	public List<Product> findByProductCategory(ProductCategory ProductCategory);
	
	public List<Product> findByDatePosted(LocalDate date);
	
	public List<Product> findByProductOwnerUsername(String username);

	public List<Product> findByLocationCity(String city);
}

