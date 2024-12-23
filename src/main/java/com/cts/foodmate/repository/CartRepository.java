package com.cts.foodmate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.foodmate.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	

	List<Cart> findAllByUserIdAndInorder(long userId,boolean inorder);

}
