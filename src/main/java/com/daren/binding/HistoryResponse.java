package com.daren.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HistoryResponse {

	private String recevierPhoneno;
	private Integer amountToTransfer;
	private String status;
	private LocalDate createdDate;

}
