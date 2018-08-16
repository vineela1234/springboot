package com.cg.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.BankWalletAccount;
import com.cg.exception.BankException;
import com.cg.service.IWalletService;
import com.cg.service.WalletServiceImpl;

public class WalletTest {

	private IWalletService service;

	@Before
	public void init() {
		service = new WalletServiceImpl();
	}

	@Test
	public void testCreateAccountForPhoneNum() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("6672456");
		account.setCustName("Viny");
		account.setEmail("viny@gmail.com");
		account.setBalanceAmount(2345.67);
		try {
			service.createAccount(account);
		} catch (BankException e) {
		assertEquals("Mobile number should be 10 digits",
					e.getMessage());
		}
	}

	@Test
	public void testCreateAccountForCustName() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245612890");
		account.setCustName("898");
		account.setEmail("vineela@gmail.com");
		account.setBalanceAmount(52345.67);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Name should start with a Capital Letter and must contain alphabets only !",
					e.getMessage());
		}
	}
	
	@Test
	public void testCreateAccountForCustName2() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245612890");
		account.setCustName("vineela");
		account.setEmail("vineela@gmail.com");
		account.setBalanceAmount(52345.67);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Name should start with a Capital Letter and must contain alphabets only !",
					e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForCustName3() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245612890");
		account.setCustName("vine_ela");
		account.setEmail("vineela@gmail.com");
		account.setBalanceAmount(52345.67);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Name should start with a Capital Letter and must contain alphabets only !",
					e.getMessage());
		}
	}


	
	@Test
	public void testCreateAccountForCustNameIsEmpty() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245612890");
		account.setCustName("");
		account.setEmail("karunya@gmail.com");
		account.setBalanceAmount(52345.67);
		try {
			service.createAccount(account);
		} catch (BankException e) {
	assertEquals("Name cannot be empty", e.getMessage());
		}
	}

	@Test
	public void testCreateAccountForEmail() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245634598");
		account.setCustName("Harika");
		account.setEmail("harika@gmail.co.in");
		account.setBalanceAmount(237);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Email Id is Not Valid", e.getMessage());
		}
	}


	@Test
	public void testCreateAccountForEmailWithNumbers() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245634598");
		account.setCustName("Harika");
		account.setEmail("1238790@gmail.co.in");
		account.setBalanceAmount(237);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Email Id is Not Valid", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForEmailWithSpecialCharecters() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245634598");
		account.setCustName("Harika");
		account.setEmail("!^&$*&@gmail.co.in");
		account.setBalanceAmount(237);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Email Id is Not Valid", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForEmailWithCapitals() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245634598");
		account.setCustName("Harika");
		account.setEmail("VINNY@gmail.co.in");
		account.setBalanceAmount(237);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Email Id is Not Valid", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForBalanceAmount() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("7245634598");
		account.setCustName("Harika");
		account.setEmail("harika@gmail.co.in");
		account.setBalanceAmount(-10);
		try {
			service.createAccount(account);
		} catch (BankException e) {
			assertEquals("Balance cannot be less than zero", e.getMessage());
		}
	}

	@Test
	public void testCreateAccount() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("9326379347");
		account.setCustName("Sindhu");
		account.setEmail("sindhu@gmail.com");
		account.setBalanceAmount(345);
		try {
			String s = service.createAccount(account);
			assertNotNull(s);
		} catch (BankException e) {
			assert (e.getMessage()) != null;
		}
	}

	@Test
	public void testShowBalanceForPhoneNumDoesNotExist() {
		try {
			service.showBalance("8987562331");
		} catch (BankException e) {
			assertEquals("Mobile number does not exists", e.getMessage());
		}
	}
	@Test
	public void testShowBalanceForPhoneNum2() {
		try {
			service.showBalance("a bcdefghu");
		} catch (BankException e) {
			assertEquals("Mobile number should be 10 digits", e.getMessage());
		}
	}


	@Test
	public void testShowBalanceForPhoneNum() {
		try {
			service.showBalance("9848516565");
		} catch (BankException e) {
			assertEquals("Mobile number should contain 10 digits",
					e.getMessage());
		}
	}



	@Test
	public void testDepositForPhoneNumDoesNotExist() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("8987562331");
		try {
			service.deposit(account.getPhoneNum(), 200);
		} catch (BankException e) {
			assertEquals("Mobile number does not exists", e.getMessage());
		}
	}

	@Test
	public void testDepositForPhoneNum() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("9999340");
		try {
			service.deposit(account.getPhoneNum(), 500);
		} catch (BankException e) {
			assertEquals("Mobile number should be 10 digits",
					e.getMessage());
		}
	}

	@Test
	public void testDepositForDepositAmount() {
		
		
		try {
			service.deposit("9966303099", -1);
		} catch (BankException e) {
			assertEquals("Deposit amount must be greater than zero",
					e.getMessage());
		}
	}

	@Test
	public void testDeposit() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("9790971020");
		try {
			service.deposit(account.getPhoneNum(), 350);
			assertNotNull(account);
		} catch (BankException e) {
			assert (e.getMessage()) != null;
		}
	}

	@Test
	public void testWithdrawForMobile() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("87873408");
		try {
			service.withdraw(account.getPhoneNum(), 500);
		} catch (BankException e) {
			assertEquals("Mobile number should be 10 digits",
					e.getMessage());
		}
	}

	@Test
	public void testWithdrawForPhoneNumDoesNotExist() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("8220340598");
		try {
			service.withdraw(account.getPhoneNum(), 2000);
		} catch (BankException e) {
			assertEquals("Mobile number does not exists", e.getMessage());
		}
	}

	@Test
	public void testWithdrawForWithdrawAmount() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("8220340598");
		try {
			service.withdraw(account.getPhoneNum(), -500);
		} catch (BankException e) {
			assertEquals(
					" Withdraw Amount must be greater than balance amount and greater than zero",
					e.getMessage());
		}
	}

	@Test
	public void testWithdraw() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("9790971020");
		try {
			service.withdraw(account.getPhoneNum(), 350);
			assertNotNull(account);
		} catch (BankException e) {
			assert (e.getMessage()) != null;
		}
	}

	@Test
	public void testFundTransferForPhoneNumDoesNotExist() {
		BankWalletAccount account1 = new BankWalletAccount();
		BankWalletAccount account2 = new BankWalletAccount();
		account1.setPhoneNum("8220340598");
		account2.setPhoneNum("9848516565");
		try {
			service.fundTransfer(account1.getPhoneNum(),
					account2.getPhoneNum(), 600);
		} catch (BankException e) {
			assertEquals("Mobile number does not exists", e.getMessage());
		}
	}

	@Test
	public void testFundTransferForTransferAmount() {
		
		try {
			service.fundTransfer("8987562331","9966303099",-9);
		} catch (BankException e) {
			assertEquals("Deposit amount must be greater than zero",
					e.getMessage());
		}
	}

	@Test
	public void testFundTransfer() {
			try {
			assertTrue(service.fundTransfer("9889123450",
					"9889198892", 300));
		} catch (BankException e) {
			assert (e.getMessage()) != null;
		}
	}

	@Test
	public void testPrintTransactionDetails() {
		BankWalletAccount account = new BankWalletAccount();
		account.setPhoneNum("9889123450");
		try {
			BankWalletAccount account2 = service
					.printTransactionDetails(account.getPhoneNum());
			assertNotNull(account2);
		} catch (BankException e) {
			assert (e.getMessage()) != null;
		}
	}
}
