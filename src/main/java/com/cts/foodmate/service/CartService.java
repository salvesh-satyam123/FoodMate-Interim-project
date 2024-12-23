package com.cts.foodmate.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.foodmate.entity.Cart;

public interface CartService {
  public void addCart(Cart cart);
  public List<Cart> getCart(long userId);
  public void updateCart(Cart cart);
  public void deleteCart(long cartId);
  public void moveFromCartToOrder(long userId);
  
}
