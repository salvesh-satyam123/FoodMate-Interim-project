package com.cts.foodmate.dto;

import com.cts.foodmate.entity.Product;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDTO {
	
	private long userId;
	
	private int quantity;
	
	private boolean inorder;
	
	
	private Product product;
}
