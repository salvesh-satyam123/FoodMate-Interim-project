package com.cts.foodmate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.foodmate.entity.Product;
import com.cts.foodmate.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void addProduct(Product p) {
		try {
			productRepository.save(p);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Product> fetch() {
		List<Product> ar = new ArrayList<>();
		productRepository.findAll().forEach(product -> ar.add(product));
		return ar;
	}

	public void deleteProduct(long id) {

		
		try {
			productRepository.deleteById(id);
			
		}catch(Exception e) {
			throw e;
			
		}
	}

	public void updateProduct(Product p) {

		try {
			productRepository.save(p);
		} catch (Exception e) {
			throw e;
		}

	}

	public List<Product> fetchById(long id) {
		List<Product> res = new ArrayList<>();
		Optional<Product> op = productRepository.findById(id);

		if (op.isPresent()) {
			res.add(op.get());
			return res;
		}

		return res;
	}

	public List<Product> fetchByIds(long[] ids) {
		List<Long> res = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			res.add(ids[i]);
		}
		return productRepository.findAllById(res);

	}

}
