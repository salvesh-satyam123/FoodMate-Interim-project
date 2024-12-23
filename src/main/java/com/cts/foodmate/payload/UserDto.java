package com.cts.foodmate.payload;

import com.cts.foodmate.entity.UserInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
	
	private long id;
	private String name;
	private String email;

	public UserDto(UserInfo userInfo) {
		name=userInfo.getName();
		email=userInfo.getEmailId();
		id=userInfo.getId();
	}
}
