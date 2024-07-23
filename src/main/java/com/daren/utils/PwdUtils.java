package com.daren.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import java.applet.*;
@Component
public class PwdUtils {
	
	private PwdUtils() {
		
	}
	
	public static String generatePwd() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		
		return  RandomStringUtils.random(15, characters );
		
		
	}

}
