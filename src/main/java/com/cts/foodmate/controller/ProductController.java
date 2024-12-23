package com.cts.foodmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.foodmate.entity.Product;
import com.cts.foodmate.service.ProductService;

@RestController
@RequestMapping("product-rest")
public class ProductController {
	
	@Autowired
	ProductService productservice;
	

	@PostMapping("/admin/product")
	public ResponseEntity<String> addProduct( @RequestBody Product p)
	{
		return this.productservice.addProduct(p);
	}


	@GetMapping("/user/fetch")
	public List<Product> fetchProduct()
	{
		return this.productservice.fetch();
	}
	
	@DeleteMapping("/admin/deleteProduct/{id}")
	public ResponseEntity<String>deleteProduct(@PathVariable long id)
	{
		return this.productservice.deleteProduct(id);
	}
	
	@PutMapping("/admin/product")
	public ResponseEntity<String>updateProduct(@RequestBody Product p)
	{
		return this.productservice.updateProduct(p);
	}
    
    @GetMapping("/user/fetch/{Id}")
	public List<Product>fetchById(@PathVariable long Id)
	{
		return productservice.fetchById(Id);
	}
	
   
    @RequestMapping(value="/user/fetchids/{Ids}", method=RequestMethod.GET)
	public List<Product>fetchByIds(@PathVariable long[] Ids)
	{
		return productservice.fetchByIds(Ids);
	}
    
    
    
	

}
