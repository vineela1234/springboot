package com.cg.bean;

import java.time.LocalDateTime;

public class BankWalletAccount {

	private String custName;
	private String phoneNum;
	private String email;
	private double balanceAmount;
	LocalDateTime date;

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BankWalletAccount() {
		super();
	}

	public BankWalletAccount(String custName, String phoneNum, String email,
			double balanceAmount, LocalDateTime date) {
		super();
		this.custName = custName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.balanceAmount = balanceAmount;
		this.date = date;
	}
}