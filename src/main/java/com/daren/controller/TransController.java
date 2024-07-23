package com.daren.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daren.binding.HistoryResponse;
import com.daren.binding.TransferForm;
import com.daren.contants.AppConstants;
import com.daren.properties.AppProperties;
import com.daren.service.TransactionService;
import com.lowagie.text.DocumentException;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("http://localhost:5173/")
public class TransController {

	@Autowired
	private TransactionService transService;

	@Autowired
	private AppProperties appProps;

	@GetMapping("/view-history")
	public List<HistoryResponse> viewHistory() {

		return transService.transHistory();
	}

	@GetMapping("/view-balance")
	public Integer viewBalance() {

		return transService.viewBalance();
	}

	@PostMapping("/tranfer-money")
	public String transferMoney(@RequestBody TransferForm form) {
		boolean status = transService.transferFunds(form);
		if (status) {
			return appProps.getMessages().get(AppConstants.TRANS_SUCC_MSG);
		}
		return appProps.getMessages().get(AppConstants.TRANS_FAIL_MSG);

	}

	@GetMapping("/getPdf")
	public void exportPdf(HttpServletResponse response) throws IOException, DocumentException, MessagingException {
		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=transactions.pdf";
		response.setHeader(headerKey, headerValue);
		transService.exportPdf(response);
	}

}
