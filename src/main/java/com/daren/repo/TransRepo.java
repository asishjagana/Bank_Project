package com.daren.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daren.entity.TransactionDtlEntity;


@Repository
public interface TransRepo extends JpaRepository<TransactionDtlEntity, Integer> {

	TransactionDtlEntity findByRecevierPhoneno(String recevierPhoneno);
}
