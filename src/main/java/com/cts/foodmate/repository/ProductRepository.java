package com.cts.foodmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.foodmate.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
