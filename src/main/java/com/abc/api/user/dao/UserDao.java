package com.abc.api.user.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.abc.api.user.entity.UserEntity;

@Repository
public interface UserDao extends MongoRepository<UserEntity, String> {

	UserEntity findByUserIdOrId(String userId,String id);
	
	List<UserEntity> findByMobileNo(String mobileNo);
	
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);
}
