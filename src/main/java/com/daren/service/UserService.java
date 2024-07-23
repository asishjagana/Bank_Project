package com.daren.service;

import com.daren.binding.LoginForm;
import com.daren.binding.RegistrationForm;

import jakarta.mail.MessagingException;

public interface UserService {

	public boolean login(LoginForm form) throws Exception;
	public boolean registration(RegistrationForm form) throws MessagingException, Exception;
	
}
