package com.cts.foodmate.entity;

import java.util.List;

import com.cts.foodmate.dto.UserInfoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String emailId;
	private String password;
	private String role;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> address;
	
	public UserInfo(long id, String name, String emailId, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.role = role;
		//this.address = address;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", emailId=" + emailId + ", password=" + password + ", role="
				+ role + ", address=" + address + "]";
	}
	
	public UserInfo(UserInfoDTO user) {
		
		this.name=user.getName();
		this.emailId=user.getEmailId();
		this.password=user.getPassword();
		this.role=user.getRole();
	}
	
}
