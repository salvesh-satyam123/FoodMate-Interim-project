package com.cts.foodmate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.foodmate.entity.Address;
import com.cts.foodmate.entity.UserInfo;
import com.cts.foodmate.exception.UserNotExistException;
import com.cts.foodmate.repository.UserInfoRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private UserInfoRepository userInfoRepository;



	@Override
	public UserInfo addAdress(List<Address> address, long userId) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}

		
		userInfo.get().setAddress(address);
		UserInfo savedUserInfo = userInfo.get();
		UserInfo savedUser = userInfoRepository.save(savedUserInfo);
		return savedUser;

	}

	@Override
	public List<Address> getAddressOfUser(long userId) {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}
		return userInfo.get().getAddress();

	}

	@Override
	public void deleteAddress(long addressId,long userId) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}
		userInfo.get().getAddress().removeIf((x)->(x.getId()==addressId));
		
		userInfoRepository.save(userInfo.get());

	}

}
