package com.cts.foodmate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.foodmate.entity.Product;
import com.cts.foodmate.repository.ProductRepository;

public class ProductServiceTest {

	@Mock
	ProductRepository productRepository;

	Product product = null;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		product = Product.builder().productId(1L).image("TEST.JPG").name("TEST").price(60L).build();
	}

	@Test
	public void addProductTest() {
		productServiceImpl.addProduct(product);
		verify(productRepository, times(1)).save(product);

	}

	@Test
	public void addProductTestException() {

		when(productRepository.save(product)).thenThrow(new IllegalArgumentException() );
		assertThrows(IllegalArgumentException.class, () -> {
			productServiceImpl.addProduct(product);
		});

	}
	
	@Test
	public void updateProductTest() {
		productServiceImpl.updateProduct(product);
		verify(productRepository, times(1)).save(product);
	}
	
	@Test
	public void fetchByIdTest()
	{
		Product product=new Product(1L, "test", "hsajsa", 10L);
		Optional<Product> o=Optional.of(product);
		when(productRepository.findById(any())).thenReturn(o);
		
		Product res=productServiceImpl.fetchById(1).get(0);
		verify(productRepository,times(1)).findById(any());
		assertEquals(product, res);
	}

}
