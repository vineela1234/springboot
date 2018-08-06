package com.cg.dao;

import com.cg.bean.BankWalletAccount;
import com.cg.exception.BankException;

public interface IWalletDao {
	public String createAccount(BankWalletAccount account) throws BankException;

	public double showBalance(String mobileNo) throws BankException;

	public BankWalletAccount deposit(String mobileNo,double depositAmount)
	throws BankException;

	public BankWalletAccount withdraw(String mobileNo,double depositAmount) throws BankException;
	 
	//public boolean fundTransfer(String mobile1, String mobile2, double amount) throws BankException;

	public BankWalletAccount printTransactionDetails(String mobileNo)
	throws BankException;
	 
	//public BankWalletAccount getAccountDetails(String mobileNo) throws BankException;
}
