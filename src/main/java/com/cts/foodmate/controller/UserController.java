package com.cts.foodmate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.cts.foodmate.dto.UserInfoDTO;
import com.cts.foodmate.entity.AuthRequest;
import com.cts.foodmate.entity.UserInfo;
import com.cts.foodmate.payload.UserDto;
import com.cts.foodmate.repository.UserInfoRepository;
import com.cts.foodmate.service.JwtService;
import com.cts.foodmate.service.UserInfoService;

@RestController
@RequestMapping("auth")
public class UserController {

	@Autowired
	private UserInfoService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserInfoRepository repository;


	@PostMapping("/addNewUser")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}

	@GetMapping("/exception/redirect")
	public String exception() {
		throw new AccessDeniedException("not found");
	}

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			//System.out.println(authentication.isAuthenticated());
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new RuntimeException("invalid user request !");
		}
	}

	@GetMapping("/admin/userdetails")
	public List<UserInfo> getProfiles() {
		return service.getUser();
	}

	@GetMapping("/user/profile")
	public UserInfo userProfile(@RequestHeader("Authorization") String authHeader) {
		String token = null;
		String username = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}

		Optional<UserInfo> userDetail = repository.findByEmailId(username);
	//	UserDto user = new UserDto(userDetail.get());
		return userDetail.get();
	}
}
