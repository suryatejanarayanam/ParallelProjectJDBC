package com.cg.wallet.dao;

public interface QueryMapper {
	public String insert="insert into account(name,email,mobileno,balance) values(?,?,?,?)";
	public String getBal="select balance from account where mobileno=?";
	public String getAcc="select * from account where mobileno=?";
	public String depositBal="update account set balance=? where mobileno=?";

}
