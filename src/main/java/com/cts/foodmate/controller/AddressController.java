package com.cts.foodmate.controller;

import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.foodmate.dto.AddressDTO;
import com.cts.foodmate.entity.UserInfo;
import com.cts.foodmate.service.AddressService;

import jakarta.validation.Valid;


@RequestMapping("address-rest")
@RestController
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
//	public static final Logger logger = LogManager.getLogger(AddressController.class);
	
	
	@PostMapping("/addAddress/{userId}")
	public UserInfo get(@RequestBody @Valid List<AddressDTO> address,@PathVariable long userId) {
		
		return addressService.addAdress(address,userId);
	}
	
	
	@GetMapping("/getAddress/{userId}")
	public List<AddressDTO> getAddressOfUser(@PathVariable long userId )
	{
		return addressService.getAddressOfUser(userId);
	}
	
	
	@DeleteMapping("/deleteAddress/{addressId}/{userId}")
	public void deleteAddress(@PathVariable long addressId,@PathVariable long userId)
	{
		addressService.deleteAddress(addressId,userId);
	}
}
