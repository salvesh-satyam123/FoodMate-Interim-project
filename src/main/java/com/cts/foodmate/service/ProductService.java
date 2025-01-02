package com.cts.foodmate.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.foodmate.entity.Product;

public interface ProductService {

	public void addProduct(Product p);
	public List<Product>fetch();
	public void deleteProduct(long id);
	public void updateProduct(Product p);
	public List<Product> fetchById(long id);
	public List<Product> fetchByIds(long []ids);
	
}
