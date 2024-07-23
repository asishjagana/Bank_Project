package com.daren.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender emailSender;

	public boolean sendMail(String to, String subject, String body) throws MessagingException {
		boolean isSent = false;

		try {

			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);

			emailSender.send(message);

			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSent;
	}
	 public void sendMessageWithAttachment(String to, String subject, String text, File pathToAttachment) throws MessagingException {
	       
	        MimeMessage message = emailSender.createMimeMessage();
	         
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        
	       
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(text);
	            
	        helper.addAttachment("transactions-info", pathToAttachment);

	        emailSender.send(message);
	        
	    }
	
}
