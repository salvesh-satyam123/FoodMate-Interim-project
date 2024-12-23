package com.cts.foodmate.service;

import java.util.List;

import com.cts.foodmate.entity.Address;
import com.cts.foodmate.entity.UserInfo;

public interface AddressService {
	
	public UserInfo addAdress(List<Address> address,long userId);
	public List<Address>getAddressOfUser(long userId);
	void deleteAddress(long addressId, long userId);

}
