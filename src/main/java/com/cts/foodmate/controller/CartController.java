package com.cts.foodmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.foodmate.entity.Cart;
import com.cts.foodmate.service.CartService;

@RestController
@RequestMapping("cart-rest")
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("/addCart")
	public void addCart(@RequestBody Cart cart) {
		cartService.addCart(cart);
	}

	@GetMapping("/cart/{userId}")
	public List<Cart> getCartsofUser(@PathVariable long userId) {
		return cartService.getCart(userId);
	}
	
	@PutMapping("/updateCart")
	public void updateCartsofUser(@RequestBody Cart cart) {
		 cartService.updateCart(cart);
	}
	
	@DeleteMapping("/deleteCart/{cartId}")
	public void  deleteCart(@PathVariable long cartId) {
		 cartService.deleteCart(cartId);
	}
	
	

}
