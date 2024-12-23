package com.cts.foodmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.foodmate.entity.Address;
import com.cts.foodmate.entity.UserInfo;
import com.cts.foodmate.service.AddressService;


@RequestMapping("address-rest")
@RestController
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@PostMapping("/addAddress/{userId}")
	public UserInfo get(@RequestBody List<Address> address,@PathVariable long userId) {
		return addressService.addAdress(address,userId);
	}
	@GetMapping("/getAddress/{userId}")
	public List<Address> getAddressOfUser(@PathVariable long userId )
	{
		return addressService.getAddressOfUser(userId);
	}
	@DeleteMapping("/deleteAddress/{addressId}/{userId}")
	public void deleteAddress(@PathVariable long addressId,@PathVariable long userId)
	{
		addressService.deleteAddress(addressId,userId);
	}
}
