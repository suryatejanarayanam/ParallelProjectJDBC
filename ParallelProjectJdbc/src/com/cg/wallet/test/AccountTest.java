package com.cg.wallet.test;


import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import com.cg.wallet.bean.Account;
import com.cg.wallet.exception.AccountException;
import com.cg.wallet.service.AccountService;
import com.cg.wallet.service.AccountServiceImp;

public class AccountTest {

	AccountService accService=new AccountServiceImp();
	
	@Test
	public void testCreateAccountMobileNum()  {
		Account acc=new Account();
		acc.setBalance(2000.0);
		acc.setEmailid("surya@gmail.com");
		acc.setMobileNo("970481434");
		acc.setName("surya");
		
		try {
			accService.createAccount(acc);
		
		} catch (AccountException e) {
			
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
		
	}
	@Test
	public void testCreateAccountName() {
		Account acc=new Account();
		acc.setBalance(2000.0);
		acc.setEmailid("surya@gmail.com");
		acc.setMobileNo("9704814343");
		acc.setName("surya075");
		try {
			accService.createAccount(acc);
		} catch (AccountException e) {
			assertEquals("Name should contain only alphabets",e.getMessage());
		}
	}
	@Test
	public void testCreateAccountMailId() {
		Account acc=new Account();
		acc.setBalance(2000.0);
		acc.setEmailid("surya075@gmail");
		acc.setMobileNo("9704814343");
		acc.setName("surya");
		try {
			accService.createAccount(acc);
		} catch (AccountException e) {
			assertEquals("Invalid mailId",e.getMessage());
		}
	}
	@Test
	public void testCreateAccountAmount() {
		Account acc=new Account();
		acc.setBalance(-2000.0);
		acc.setEmailid("surya@gmail.com");
		acc.setMobileNo("9704814343");
		acc.setName("surya");
		try {
			accService.createAccount(acc);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	@Test
	public void testCreateAccount() {
		Account acc=new Account();
		acc.setBalance(5000.0);
		acc.setEmailid("surya@gmail.com");
		acc.setMobileNo("9704814343");
		acc.setName("surya");
		try {
			String mobileNo = accService.createAccount(acc);
			assertNotNull(mobileNo);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testShowBalanceMobileNum()  {
		try {
			accService.showBalance("97048143");
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	
	
	@Test
	public void testDepositMobileNum()  {
		try {
			accService.deposit("97048143",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testDepositAmount() {
		try {
			accService.deposit("9704814343",-1.0);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	@Test
	public void testDeposit() {
		Account acc=new Account();
		acc.setMobileNo("9704814344");
		try {
			double ac= accService.deposit(acc.getMobileNo(),500);
			assertEquals(4500.0, ac,0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testWithdrawMobileNum()  {
		try {
			accService.withdraw("97048143",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testWithdrawAmount()  {
		try {
			accService.withdraw("9704814344",-1.0);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	
	
	@Test
	public void testWithdraw()  {
		Account acc=new Account();
		acc.setMobileNo("9704814345");
		try {
			double acc1 = accService.withdraw(acc.getMobileNo(),100);
			assertEquals(600.0, acc1,0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			
		}
		}
	@Test
	public void testFundTransferMobileNo1()  {
		try {
			accService.fundTransfer("9370481","9704814344",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testFundTransferMobileNo2()  {
		try {
			accService.fundTransfer("9876543210","98765432",50.0);
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	@Test
	public void testFundTransferAmount()
	{
		try {
			accService.fundTransfer("9704814345","9704814344",-1);
		} catch (AccountException e) {
			assertEquals("Enter Valid Amount should be greater than zero",e.getMessage());
		}
	}
	
	
	@Test
	public void testFundTransfer()  
	{
		Account acc=new Account();
		Account acc1=new Account();
		try {
			boolean b = accService.fundTransfer("9704814345","9704814344",100);
			assertNotNull(b);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
		}
	@Test
	public void testPrintTransactionMobileNo()  {
		try {
			accService.printTransactions("9704814");
		} catch (AccountException e) {
			assertEquals("Mobile Number should contain 10 digits",e.getMessage());
		}
	}
	
	@Test
	public void testPrintTransactions()  {
		Account acc=new Account();
		try {
			Account acc1 = accService.printTransactions("9704814345");
			assertNotNull(acc1);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			
		}
		}

	

}
