package com.daren.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daren.binding.HistoryResponse;
import com.daren.binding.TransferForm;
import com.daren.entity.BalanceEntity;
import com.daren.entity.TransactionDtlEntity;
import com.daren.entity.UserDtlEntity;
import com.daren.repo.TransRepo;
import com.daren.repo.UserRepo;
import com.daren.utils.EmailUtils;
import com.daren.utils.PdfGenerator;
import com.lowagie.text.DocumentException;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class TransServiceImpl implements TransactionService {

	@Autowired
	private HttpSession session;
	@Autowired
	private TransRepo transRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PdfGenerator pdfGenerator;
	@Autowired
	private EmailUtils emailSender;

	@Override
	public List<HistoryResponse> transHistory() {
		// TODO get data from database based on user
		Integer userId = (Integer) session.getAttribute("userId");
		UserDtlEntity user = userRepo.findById(userId).get();
		
		List<HistoryResponse> response = new ArrayList<>();
		
		List<TransactionDtlEntity> transactions = user.getTransactions();
		for(TransactionDtlEntity tx:transactions) {
			HistoryResponse t = new HistoryResponse();
			t.setRecevierPhoneno(tx.getRecevierPhoneno());
			t.setAmountToTransfer(tx.getAmountToTransfer());
			t.setStatus(tx.getStatus());
			t.setCreatedDate(tx.getCreatedDate());
			
			response.add(t);
		}
		return response;
	}

	@Override
	public Integer viewBalance() {
		Integer userId = (Integer) session.getAttribute("userId");
		UserDtlEntity user = userRepo.findById(userId).get();
		BalanceEntity balance = user.getBalance();
		return balance.getAmount();
		
		 
	}

	@Override
	public boolean transferFunds(TransferForm form) {
		// TODO get user dtls and sender dtls
		Integer userId = (Integer) session.getAttribute("userId");
		UserDtlEntity sender = userRepo.findById(userId).get();
		//String senderPhoneNo = sender.getPhoneno();
		String receiverPhoneNo = form.getRecevierPhoneno();
		UserDtlEntity recevier = userRepo.findByPhoneno(receiverPhoneNo);
		
		if(recevier!=null  ) {
			BalanceEntity senderBalance = sender.getBalance();
			BalanceEntity recevierBalance = recevier.getBalance();
			Integer amountToTransfer = form.getAmountToTransfer();
			if(senderBalance.getAmount() <= amountToTransfer) {
				return false;
			}
			senderBalance.setAmount((senderBalance.getAmount()-amountToTransfer));
			recevierBalance.setAmount(recevierBalance.getAmount()+amountToTransfer);
			
		}
		TransactionDtlEntity trans = new TransactionDtlEntity();
		BeanUtils.copyProperties(form, trans);
		trans.setStatus("SUCCESSFULL");
		trans.setUser(sender);
		transRepo.save(trans);
		// modify the db

		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws DocumentException, IOException, MessagingException {

		File file = new File("transactions.pdf");

		
		
		Integer userId = (Integer) session.getAttribute("userId");
		UserDtlEntity sender = userRepo.findById(userId).get();
		List<TransactionDtlEntity> transactions = sender.getTransactions();
		pdfGenerator.generatePdf(response, transactions, file);
		
		String to = sender.getEmail();
		String text = "Transaction History";
		String subject = "Find Your Transaction History";

		emailSender.sendMessageWithAttachment(to, subject, text, file);
		file.delete();

		return true;

	}

}
