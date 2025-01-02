package com.cts.foodmate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.foodmate.dto.AddressDTO;
import com.cts.foodmate.entity.Address;
import com.cts.foodmate.entity.UserInfo;
import com.cts.foodmate.exception.UserNotExistException;
import com.cts.foodmate.repository.UserInfoRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo addAdress(List<AddressDTO> addressDto, long userId) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}
		List<Address> addressList = new ArrayList<>();
		for (AddressDTO dto : addressDto) {
			Address address = new Address();
			address.setCity(dto.getCity());
			address.setState(dto.getState());
			address.setStreet(dto.getStreet());
			address.setZipCode(dto.getZipCode());
			addressList.add(address);

		}
		userInfo.get().setAddress(addressList);
		UserInfo savedUserInfo = userInfo.get();
		UserInfo savedUser = userInfoRepository.save(savedUserInfo);
		return savedUser;

	}

	@Override
	public List<AddressDTO> getAddressOfUser(long userId) {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}
		List<Address> address= userInfo.get().getAddress();
		
		List<AddressDTO>addressDto=new ArrayList<>();
		for(Address a:address) {
			AddressDTO obj=new AddressDTO();
			obj.setCity(a.getCity());
			obj.setState(a.getState());
			obj.setStreet(a.getStreet());
			obj.setZipCode(a.getZipCode());
			addressDto.add(obj);
			
		}
		return addressDto;

	}

	@Override
	public void deleteAddress(long addressId, long userId) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}
		userInfo.get().getAddress().removeIf((x) -> (x.getId() == addressId));

		userInfoRepository.save(userInfo.get());

	}

}
