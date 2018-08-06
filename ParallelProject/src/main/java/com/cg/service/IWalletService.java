package com.cg.service;

import com.cg.bean.BankWalletAccount;
import com.cg.exception.BankException;

public interface IWalletService {

	public String createAccount(BankWalletAccount account) throws BankException;

	public double showBalance(String mobileNo) throws BankException;

	public BankWalletAccount deposit(String mobileNo, double depositAmount)
			throws BankException;

	public BankWalletAccount withdraw(String mobileNo, double withdrawAmount)
			throws BankException;

	public BankWalletAccount printTransactionDetails(String mobileNo)
			throws BankException;

	boolean fundTransfer(String sourceMobileNo, String destMobileNo,
			double transferAmount) throws BankException;
}
