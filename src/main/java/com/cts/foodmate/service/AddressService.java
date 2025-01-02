package com.cts.foodmate.service;

import java.util.List;

import com.cts.foodmate.dto.AddressDTO;
import com.cts.foodmate.entity.Address;
import com.cts.foodmate.entity.UserInfo;

public interface AddressService {
	
	public UserInfo addAdress(List<AddressDTO> addressDto, long userId);
	public List<AddressDTO> getAddressOfUser(long userId);
	void deleteAddress(long addressId, long userId);

}
