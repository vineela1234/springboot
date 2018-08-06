package com.cg;

import com.cg.bean.BankWalletAccount;
import com.cg.exception.BankException;
import com.cg.service.IWalletService;
import com.cg.service.WalletServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      IWalletService service=new WalletServiceImpl();
      BankWalletAccount acc=new BankWalletAccount();
      acc.setCustName("Deepika");
      acc.setPhoneNum("9848468242");
      acc.setEmail("deepu@gmail.com");
      acc.setBalanceAmount(23000);
      try {
		String m=service.createAccount(acc);
		System.out.println(m);
	} catch (BankException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
