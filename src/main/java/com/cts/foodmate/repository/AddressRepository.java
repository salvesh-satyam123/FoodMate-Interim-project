package com.cts.foodmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.foodmate.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
