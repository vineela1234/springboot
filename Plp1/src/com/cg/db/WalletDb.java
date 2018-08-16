package com.cg.db;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.cg.bean.BankWalletAccount;

public class WalletDb {
	private static HashMap<String, BankWalletAccount> walletDb = new HashMap<String, BankWalletAccount>();

	public static HashMap<String, BankWalletAccount> getWalletDb() {
		return walletDb;
	}

	static {
		walletDb.put("9966303099", new BankWalletAccount("Rekha", "9966303099",
				"rekha1973@gmail.com", 5000.00, LocalDateTime.now()));
		walletDb.put("9440616565",
				new BankWalletAccount("Vrushali", "9440616565",
						"vrushali188@gmail.com", 45070.57, LocalDateTime.now()));
		walletDb.put("8220340598",
				new BankWalletAccount("Krishna", "8220340598",
						"krishnajv@gmail.com", 38000.23, LocalDateTime.now()));
		walletDb.put("8987562331",
				new BankWalletAccount("Aashiq", "8987562331",
						"aashiq15@gmail.com", 345678.90, LocalDateTime.now()));
		walletDb.put("9848516565", new BankWalletAccount("Srinu", "9848516565",
				"srinu@gmail.com", 76000, LocalDateTime.now()));
	}
}
