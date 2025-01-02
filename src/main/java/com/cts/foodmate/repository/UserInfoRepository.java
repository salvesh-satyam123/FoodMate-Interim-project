package com.cts.foodmate.repository; 

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.foodmate.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findByName(String username);
	Optional<UserInfo> findByEmailId(String email);
	List<UserInfo> findAllById(long userId);
}
