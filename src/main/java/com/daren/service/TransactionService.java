package com.daren.service;

import java.io.IOException;
import java.util.List;

import com.daren.binding.HistoryResponse;
import com.daren.binding.TransferForm;
import com.lowagie.text.DocumentException;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;

public interface TransactionService {

	public List<HistoryResponse> transHistory();

	public Integer viewBalance();

	public boolean transferFunds(TransferForm form);

	boolean exportPdf(HttpServletResponse response) throws DocumentException, IOException, MessagingException;

}
