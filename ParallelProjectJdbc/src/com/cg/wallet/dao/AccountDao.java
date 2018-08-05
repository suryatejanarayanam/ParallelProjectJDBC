package com.cg.wallet.dao;

import com.cg.wallet.bean.Account;
import com.cg.wallet.exception.AccountException;

public interface AccountDao {
	String createAccount(Account account) throws AccountException;
	double showBalance(String mobileNo) throws AccountException;
	double deposit(String mobileNo,double amount) throws AccountException;
	double withdraw(String mobileNo,double amount) throws AccountException;
	boolean fundTransfer(String mobileNo1,String mobileNo2,double amount) throws AccountException;
	Account printTransactions(String mobileNo) throws AccountException;
}
