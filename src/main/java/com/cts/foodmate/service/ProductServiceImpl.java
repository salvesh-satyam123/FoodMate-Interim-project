package com.cts.foodmate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.foodmate.entity.Product;
import com.cts.foodmate.repository.ProductDao;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productdao;
	
	public ResponseEntity<String> addProduct(Product p)
	{
		try {
			productdao.save(p);
			return new ResponseEntity<>(
			          "Product Added", 
			          HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(
			          "Unable to Add", 
			          HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<Product>fetch()
	{
		List<Product>ar=new ArrayList<>();
		   productdao.findAll()
		   .forEach(product->ar.add(product));
		   return ar;
	}
	
	public ResponseEntity<String> deleteProduct(long id)
	{
		try {
			productdao.deleteById(id);
			return new ResponseEntity<>(
			          "Product Deleted", 
			          HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(
			          "Error Occured", 
			          HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ResponseEntity<String> updateProduct(Product p)
	{
		try {
			productdao.save(p);
			return new ResponseEntity<>(
			          "Product Updated", 
			          HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(
			          "Error Occured", 
			          HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<Product> fetchById(long id)
	{
		List<Product>res=new ArrayList<>();
		Optional<Product>op=productdao.findById(id);
		
			if ( op.isPresent() )
			{
			    res.add(op.get());
			  return res;
			}
			
			return res; 
	}
	
	public List<Product> fetchByIds(long []ids)
	{
		List<Long>res=new ArrayList<>();
		for(int i=0;i<ids.length;i++)
		{
			res.add(ids[i]);
		}
		return productdao.findAllById(res);
		
	}

}
