package com.daren.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daren.entity.BalanceEntity;

@Repository
public interface BalanceRepo extends JpaRepository<BalanceEntity, Integer>{

	
	
}
