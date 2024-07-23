package com.daren.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daren.entity.UserDtlEntity;



@Repository
public interface UserRepo extends JpaRepository<UserDtlEntity, Integer> {

	//public UserDtlEntity findByUserNameAndPassword(String userName, String password);

	public UserDtlEntity findByUserNameAndPassword(String userName, String password);
	public UserDtlEntity findByPhoneno(String phoneno);
	
}
