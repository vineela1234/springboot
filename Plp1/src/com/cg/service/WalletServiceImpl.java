package com.cg.service;

import java.time.LocalDateTime;

import com.cg.bean.BankWalletAccount;
import com.cg.dao.IWalletDao;
import com.cg.dao.WalletDaoImpl;
import com.cg.exception.BankException;

public class WalletServiceImpl implements IWalletService {

	IWalletDao walletDao = new WalletDaoImpl();

	@Override
	public String createAccount(BankWalletAccount account) throws BankException {
		// TODO Auto-generated method stub
		if (!account.getPhoneNum().matches("\\d{10}")) {
			throw new BankException("Mobile number should be 10 digits");
		}
		if (account.getCustName().isEmpty()
				|| account.getCustName() == null) {
			throw new BankException("Name cannot be empty");
		}
		 else {
			
			if (!account.getCustName().matches("[A-Z][A-Za-z]{2,}")) {
				throw new BankException(
						"Name should start with a Capital Letter and must contain alphabets only !");
			}
		}
		if (account.getEmail().matches("[a-z0-9]{2,}+@{1}+[a-z]{2,}+\\.com")) {
			throw new BankException(
					"Email id does not mach the given criteria .");
		}
		if (account.getBalanceAmount() <= 0) {
			throw new BankException("Balance cannot be less than zero");
		}
		return walletDao.createAccount(account);
	}

	@Override
	public double showBalance(String mobileNo) throws BankException {
		// TODO Auto-generated method stub
		if (!mobileNo.matches("\\d{10}")) {
			throw new BankException("Mobile number should be 10 digits");
		}
		return walletDao.showBalance(mobileNo);
	}

	@Override
	public BankWalletAccount deposit(String mobileNo, double depositAmount)
			throws BankException {
		// TODO Auto-generated method stub

		if (!mobileNo.matches("\\d{10}")) {
			throw new BankException("Mobile number should be 10 digits");
		}
		BankWalletAccount account = walletDao.deposit(mobileNo, depositAmount);
		if (depositAmount <= 0) {
			throw new BankException("Deposit amount must be greater than zero");
		}
		account.setBalanceAmount(account.getBalanceAmount() + depositAmount);
		account.setDate(LocalDateTime.now());
		return account;
	}

	@Override
	public BankWalletAccount withdraw(String mobileNo, double withdrawAmount)
			throws BankException {
		// TODO Auto-generated method stub
		if (!mobileNo.matches("\\d{10}")) {
			throw new BankException("Mobile number should be 10 digits");
		}
		BankWalletAccount account = walletDao.withdraw(mobileNo);
		if (withdrawAmount >= account.getBalanceAmount()) {
			throw new BankException(
					"Withdraw amount cannot be less than available balance");
		}
		account.setBalanceAmount(account.getBalanceAmount() - withdrawAmount);
		account.setDate(LocalDateTime.now());
		return account;
	}

	@Override
	public BankWalletAccount printTransactionDetails(String mobileNo)
			throws BankException {
		// TODO Auto-generated method stub
		return walletDao.printTransactionDetails(mobileNo);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean fundTransfer(String sourceMobileNo, String destMobileNo,
			double transferAmount) throws BankException {
		// TODO Auto-generated method stub

		if (!sourceMobileNo.matches("\\d{10}")) {
			throw new BankException("Mobile number should be 10 digits");
		}
		if (!destMobileNo.matches("\\d{10}")) {
			throw new BankException("Mobile number should be 10 digits");
		}
		IWalletService walletService = new WalletServiceImpl();
		BankWalletAccount acc1 = walletService.withdraw(sourceMobileNo,
				transferAmount);
		BankWalletAccount acc2 = walletService.deposit(destMobileNo,
				transferAmount);
		return true;
	}

}
