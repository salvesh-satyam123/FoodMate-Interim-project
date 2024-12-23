package com.cts.foodmate.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.foodmate.dto.UserInfoDTO;
import com.cts.foodmate.entity.UserInfo;
import com.cts.foodmate.exception.UserAlreadyExistsException;
import com.cts.foodmate.exception.UserNotExistException;
import com.cts.foodmate.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userDetail = Optional.ofNullable(repository.findByEmailId(username).orElseThrow(() -> new UserNotExistException("User not found " + username)));

		Set<GrantedAuthority>authorities=null;
		
		if(userDetail.isPresent())
		{
		String role=userDetail.get().getRole();
         authorities=Set.of(new SimpleGrantedAuthority("ROLE_"+role));
		}
		
		return new org.springframework.security.core.userdetails.User(userDetail.get().getEmailId(),userDetail.get().getPassword(),true,true,true,true,authorities);
		

	}

	public String addUser(UserInfo userInfo) {

		Optional<UserInfo> u = repository.findByEmailId(userInfo.getEmailId());

		if (u.isPresent()) {
			throw new UserAlreadyExistsException("Already exists");
		}

		userInfo.setPassword(encoder.encode(userInfo.getPassword()));

		repository.save(userInfo);

		return "Saved successfully";

	}

	public List<UserInfo> getUser() {
		return repository.findAll();
	}

	public UserInfo getUserById(long userid) {
		Optional<UserInfo> user = repository.findById(userid);
		if (user.isPresent()) {
			return repository.findById(userid).get();
		} else {
			throw new UsernameNotFoundException("User Not found" + userid);
		}
	}

	public boolean loadDummyData(int numberOfData) {
		List<UserInfo> users = IntStream
				.range(1, numberOfData + 1).mapToObj(i -> new UserInfo(i, "omm" + String.valueOf(i),
						"omm" + String.valueOf(i) + "@gmail.com", encoder.encode("omm" + String.valueOf(i)), "USER"))
				.collect(Collectors.toList());

		List<UserInfo> saved = repository.saveAll(users);

		if (saved.size() == numberOfData)

		{
			return true;
		}
		return false;

	}

}
