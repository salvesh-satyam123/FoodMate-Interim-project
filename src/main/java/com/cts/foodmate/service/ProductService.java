package com.cts.foodmate.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.foodmate.entity.Product;

public interface ProductService {

	public ResponseEntity<String> addProduct(Product p);
	public List<Product>fetch();
	public ResponseEntity<String> deleteProduct(long id);
	public ResponseEntity<String> updateProduct(Product p);
	public List<Product> fetchById(long id);
	public List<Product> fetchByIds(long []ids);



	
}
