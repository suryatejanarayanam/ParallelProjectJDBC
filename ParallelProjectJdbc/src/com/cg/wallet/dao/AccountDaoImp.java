package com.cg.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.cg.wallet.bean.Account;
import com.cg.wallet.dao.AccountDao;
import com.cg.wallet.db.AccountDB;
import com.cg.wallet.exception.AccountException;


public class AccountDaoImp implements AccountDao {
	
	@Override
	public String createAccount(Account account) throws AccountException {
		Connection connect=AccountDB.getConnection();
		PreparedStatement statement;
		try{
		connect.setAutoCommit(false);
		statement = connect.prepareStatement(QueryMapper.insert);
		statement.setString(1, account.getName());
		statement.setString(2, account.getEmailid());
		statement.setString(3, account.getMobileNo());
		statement.setDouble(4, account.getBalance());
		int res=statement.executeUpdate();
		if(res==1){
			connect.commit();
			return account.getMobileNo();
		}else{
			throw new AccountException("Account cannot be created");
		}
		} catch (SQLException e) {
		
		throw new AccountException(e.getMessage());
	}
	}
    @Override
	public double showBalance(String mobileNo) throws AccountException {
    	Connection connect=AccountDB.getConnection();
		PreparedStatement statement;
		try{
			statement = connect.prepareStatement(QueryMapper.getBal);
			statement.setString(1,mobileNo);
			ResultSet res=statement.executeQuery();
			connect.commit();
			if(res!=null)
			{
				res.next();
				return res.getDouble("balance");
			}
			else
			{
				throw new AccountException("Account with this mobileno does not exist");
			}
		}catch (SQLException e) {
			
			throw new AccountException(e.getMessage());
		}
			
	}
@Override
	public double deposit(String mobileNo, double amount)
			throws AccountException {
	Connection connect=AccountDB.getConnection();
	PreparedStatement statement;
	PreparedStatement statement1;
	double balance1=0;
	try{
		statement = connect.prepareStatement(QueryMapper.getAcc);
		statement.setString(1,mobileNo);
		ResultSet res=statement.executeQuery();
		connect.commit();
		if(res!=null)
		{
			
			res.next();
			Account acc=new Account();
			double balance=res.getDouble("balance")+amount;
			acc.setName(res.getString("name"));
			acc.setEmailid(res.getString("email"));
			acc.setMobileNo(res.getString("mobileno"));
			acc.setBalance(balance);
			statement1 = connect.prepareStatement(QueryMapper.depositBal);
			statement1.setDouble(1,acc.getBalance());
			statement1.setString(2,acc.getMobileNo());
			int rs=statement1.executeUpdate();			
			if(rs==1)
			{
				connect.commit();
				balance1=acc.getBalance();
			}
		
		else{
			throw new AccountException("Account with this mobileno does not exist");
		}
			return balance1;
		}
		else
		{
			throw new AccountException("Account with this mobileno does not exist");
		}
	}catch (SQLException e) {
		
		throw new AccountException(e.getMessage());
	}
}
@Override
	public double withdraw(String mobileNo, double amount)
			throws AccountException {
	Connection connect=AccountDB.getConnection();
	PreparedStatement statement;
	PreparedStatement statement1;
	double balance1=0;
	try{
		statement = connect.prepareStatement(QueryMapper.getAcc);
		statement.setString(1,mobileNo);
		ResultSet res=statement.executeQuery();
		connect.commit();
		if(res!=null)
		{
			
			res.next();
			Account acc=new Account();
			if(res.getDouble("balance")<amount){
			throw new AccountException("Enter amount less than existing amount");
			}else{
			double balance=res.getDouble("balance")-amount;
			acc.setName(res.getString("name"));
			acc.setEmailid(res.getString("email"));
			acc.setMobileNo(res.getString("mobileno"));
			acc.setBalance(balance);
			statement1 = connect.prepareStatement(QueryMapper.depositBal);
			statement1.setDouble(1,acc.getBalance());
			statement1.setString(2,acc.getMobileNo());
			int rs=statement1.executeUpdate();			
			if(rs==1)
			{
				connect.commit();
				balance1=acc.getBalance();
			}
		
		else{
			throw new AccountException("Account with this mobileno does not exist");
		}
			return balance1;
		}
		}
		else
		{
			throw new AccountException("Account with this mobileno does not exist");
		}
	}catch (SQLException e) {
		
		throw new AccountException(e.getMessage());
	}
	}

	@Override
	public boolean fundTransfer(String mobileNo1, String mobileNo2, double amount)
			throws AccountException {
		double bal=deposit(mobileNo1, amount);
	double bal1=withdraw(mobileNo2, amount);
	return true;
			}
	@Override
	public Account printTransactions(String mobileNo) throws AccountException {
		Connection connect=AccountDB.getConnection();
		PreparedStatement statement;
		try{
		statement = connect.prepareStatement(QueryMapper.getAcc);
		statement.setString(1,mobileNo);
		ResultSet res=statement.executeQuery();
		connect.commit();
		if(res!=null)
		{
			res.next();
			Account acc=new Account();
			acc.setName(res.getString("name"));
			acc.setEmailid(res.getString("email"));
			acc.setMobileNo(res.getString("mobileno"));
			acc.setBalance(res.getDouble("balance"));
		return acc;
		}
		else
		{
			throw new AccountException("Account with this mobileno does not exist");
		}
	}catch (SQLException e) {
		
		throw new AccountException(e.getMessage());
	}
	}

}
