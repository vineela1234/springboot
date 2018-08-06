package com.cg.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankWalletAccount {
	@Id@GeneratedValue
	private int id;
	private String custName;
	private String phoneNum;
	private String email;
	private double balanceAmount;
	Date date;


	public BankWalletAccount(int id, String custName, String phoneNum, String email, double balanceAmount,
			Date date) {
		super();
		this.id = id;
		this.custName = custName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.balanceAmount = balanceAmount;
		this.date = date;
	}


	public BankWalletAccount() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BankWalletAccount [id=" + id + ", custName=" + custName + ", phoneNum=" + phoneNum + ", email=" + email
				+ ", balanceAmount=" + balanceAmount + ", date=" + date + "]";
	}


}