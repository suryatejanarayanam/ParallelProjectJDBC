package com.cg.wallet.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Account {

private String name;
private String emailid;
private double balance;
private String mobileNo;
LocalDateTime localdate;

public Account() {
	super();
}


public Account(String name, String emailid, double balance, String mobileNo) {
	super();
	this.name = name;
	this.emailid = emailid;
	this.balance = balance;
	this.mobileNo = mobileNo;
}


public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public LocalDateTime getLocaldate() {
	return localdate;
}

public void setLocalDateTime(LocalDateTime localdate) {
	this.localdate = localdate;
}


}
