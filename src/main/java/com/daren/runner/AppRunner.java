package com.daren.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import com.daren.entity.BalanceEntity;
import com.daren.entity.TransactionDtlEntity;
import com.daren.entity.UserDtlEntity;
import com.daren.repo.UserRepo;
//@Component
public class AppRunner implements ApplicationRunner{
	@Autowired
	private UserRepo userRepo;

	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		UserDtlEntity u1 = new UserDtlEntity();
		BalanceEntity b1 = new BalanceEntity();
		TransactionDtlEntity t1 = new TransactionDtlEntity();
		TransactionDtlEntity t2 = new TransactionDtlEntity();
		t1.setAmountToTransfer(10);
		t1.setRecevierPhoneno("363782383873");
		t1.setStatus("SUCCESS");
		t1.setUser(u1);
		
		t2.setAmountToTransfer(20);
		t2.setRecevierPhoneno("763782383873");
		t2.setStatus("SUCCESS");
		t2.setUser(u1);
		
		b1.setAmount(5000);
		b1.setUser(u1);
		
		u1.setAddress("VZM");
		u1.setBalance(b1);
		u1.setEmail("tonystarkironman1696@gmail.com");
		u1.setPassword("sdgadfjadaf");
		u1.setPhoneno("99453278454");
		u1.setUserName("Saaho");
		u1.setTransactions(Arrays.asList(t1,t2));
		
		userRepo.save(u1);

		
		UserDtlEntity u2 = new UserDtlEntity();
		BalanceEntity b2 = new BalanceEntity();
		TransactionDtlEntity t3 = new TransactionDtlEntity();
		TransactionDtlEntity t4 = new TransactionDtlEntity();
		t3.setAmountToTransfer(40);
		t3.setRecevierPhoneno("443782383873");
		t3.setStatus("SUCCESS");
		t3.setUser(u2);
		
		t4.setAmountToTransfer(50);
		t4.setRecevierPhoneno("763782383873");
		t4.setStatus("SUCCESS");
		t4.setUser(u2);
		
		b2.setAmount(7000);
		b2.setUser(u2);
		
		u2.setAddress("VSKP");
		u2.setBalance(b2);
		u2.setEmail("tonystarkironman1696@gmail.com");
		u2.setPassword("ajkfhaffa");
		u2.setPhoneno("89453278454");
		u2.setUserName("Steve");
		u2.setTransactions(Arrays.asList(t3,t4));
		userRepo.save(u2);
		
	}

}
