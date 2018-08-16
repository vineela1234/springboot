package com.cg.dao;

import java.util.HashMap;

import com.cg.bean.BankWalletAccount;
import com.cg.db.WalletDb;
import com.cg.exception.BankException;

public class WalletDaoImpl implements IWalletDao {
	public static HashMap<String, BankWalletAccount> walletMap = WalletDb
			.getWalletDb();

	@Override
	public String createAccount(BankWalletAccount account) throws BankException {

		walletMap.put(account.getPhoneNum(), account);
		return account.getPhoneNum();
	}

	@Override 
	public double showBalance(String mobileNo) throws BankException {
		BankWalletAccount account = walletMap.get(mobileNo);
		if (account == null) {
			throw new BankException("Mobile Number Does Not Exists!");
		}
		return account.getBalanceAmount();
	}

	@Override
	public BankWalletAccount deposit(String mobileNo, double depositAmount)
			throws BankException {
		BankWalletAccount account = walletMap.get(mobileNo);
		if (account == null) {
			throw new BankException("Mobile Number Does Not Exists!");
		}
		return account;
	}

	@Override
	public BankWalletAccount withdraw(String mobileNo) throws BankException {
		BankWalletAccount account = walletMap.get(mobileNo);
		if (account == null) {
			throw new BankException("Mobile Number Does Not Exists!");
		}
		return account;
	}

	@Override
	public BankWalletAccount printTransactionDetails(String mobileNo)
			throws BankException {
		BankWalletAccount account = walletMap.get(mobileNo);
		if (account == null) {
			throw new BankException("Mobile Number Does Not Exists!");
		}
		return account;
	}

}
