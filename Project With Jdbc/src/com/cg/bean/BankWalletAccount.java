package com.cg.bean;

public class BankWalletAccount {

	private String custName;
	private String phoneNum;
	private String email;
	private double balanceAmount;
	String date;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BankWalletAccount() {
		super();
	}

	public BankWalletAccount(String custName, String phoneNum, String email,
			double balanceAmount, String date) {
		
		this.custName = custName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.balanceAmount = balanceAmount;
		this.date = date;
	}
}