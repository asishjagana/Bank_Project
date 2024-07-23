package com.daren.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "TRANS_DTL")
public class TransactionDtlEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transId; 
	//private String senderPhoneno;
	private String recevierPhoneno;
	
	private Integer amountToTransfer;
	private String status;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDtlEntity user;

}
