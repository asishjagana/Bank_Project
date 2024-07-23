package com.daren.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daren.binding.LoginForm;
import com.daren.binding.RegistrationForm;
import com.daren.contants.AppConstants;
import com.daren.properties.AppProperties;
import com.daren.service.UserService;

@RestController
@CrossOrigin("http://localhost:5173/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties appProps;
	
	
	@PostMapping("/login")
	public String loginUser(@RequestBody LoginForm form) throws Exception {
		
		boolean status = userService.login(form);
		if(status) {
			return appProps.getMessages().get(AppConstants.LOGIN_SUCC_MSG);
		}
		
		return appProps.getMessages().get(AppConstants.LOGIN_FAILURE_MSG);
		
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody RegistrationForm form) throws Exception {
		
		boolean status = userService.registration(form);
		if(status) {
			return appProps.getMessages().get(AppConstants.REG_SUCC_MSG);
		}
		return appProps.getMessages().get(AppConstants.REG_FAILURE_MSG);
	}

}
