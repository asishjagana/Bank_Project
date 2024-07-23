package com.daren.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daren.binding.LoginForm;
import com.daren.binding.RegistrationForm;
import com.daren.entity.BalanceEntity;
import com.daren.entity.UserDtlEntity;
import com.daren.repo.UserRepo;
import com.daren.utils.EmailUtils;
import com.daren.utils.PwdAESUtils;
import com.daren.utils.PwdUtils;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private HttpSession session;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean login(LoginForm form) throws Exception {
		
		String encrypt = PwdAESUtils.encrypt(form.getPassword());
		UserDtlEntity user = userRepo.findByUserNameAndPassword(form.getUserName(),encrypt );
		if (user == null) {
			return false;
		}else {

		// if valid user create session

		Integer userId = user.getUserId();
		session.setAttribute("userId", userId);
		

		return true;
		}
	}

	@Override
	public boolean registration(RegistrationForm form) throws Exception {
		// TODO get data and register user
		UserDtlEntity user = new UserDtlEntity();
		BalanceEntity bal = new BalanceEntity();
		BeanUtils.copyProperties(form, user);
		
		String encrypt = PwdAESUtils.encrypt( PwdUtils.generatePwd());
		bal.setAmount(5000);
		bal.setUser(user);
		user.setPassword(encrypt);
		user.setBalance(bal);
		userRepo.save(user);
		String email = form.getEmail();
		String subject = "Password To Login";
		StringBuffer body = new StringBuffer("");
		body.append("Use this  Password to Login ");

		body.append("Your PassWord: " + PwdAESUtils.decrypt(encrypt));

		boolean sendMail = emailUtils.sendMail(email, subject, body.toString());

		return sendMail;
	}

}
