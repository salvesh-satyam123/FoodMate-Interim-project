package com.cts.foodmate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.foodmate.entity.Cart;
import com.cts.foodmate.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public void addCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public List<Cart> getCart(long userId) {
		return cartRepository.findAllByUserIdAndInorder(userId, false);
	}

	@Override
	public void updateCart(Cart cart) {
	  cartRepository.save(cart);
	}

	@Override
	public void deleteCart(long cartId) {
		cartRepository.deleteById(cartId);	
	}
	
	public void moveFromCartToOrder(long userId) {
		
		List<Cart>cart=cartRepository.findAllByUserIdAndInorder(userId, false);
		for(Cart c:cart) {
			c.setInorder(true);
		}
		cartRepository.saveAll(cart);
		
		
		
		
	}

}
