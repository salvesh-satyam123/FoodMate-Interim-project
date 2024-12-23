package com.cts.foodmate.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.foodmate.entity.UserInfo;

public class UserInfoDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private GrantedAuthority authorities;

	public UserInfoDetails(UserInfo userInfo) {
		name = userInfo.getEmailId();
		password = userInfo.getPassword();
		authorities = new SimpleGrantedAuthority(userInfo.getRole());
	}

	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(authorities);
	}
}
