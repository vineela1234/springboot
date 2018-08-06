package com.cg.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.bean.BankWalletAccount;
import com.cg.exception.BankException;

public class WalletDaoImpl implements IWalletDao {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
	static private EntityManager em = emf.createEntityManager();
	@Override
	public String createAccount(BankWalletAccount account) throws BankException {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		System.out.println("Done!!");
		return account.getPhoneNum();
	}

	@Override
	public double showBalance(String mobileNo) throws BankException {
		String string = "select a from BankWalletAccount a where a.phoneNum=?";
		TypedQuery<BankWalletAccount> query = em.createQuery(string, BankWalletAccount.class);
		query.setParameter(1, mobileNo);
		BankWalletAccount ac = query.getSingleResult();
		if (mobileNo.equals(ac.getPhoneNum())) {
			return ac.getBalanceAmount();
		} else {
			throw new BankException("number doesnot exists");
		}
	}

	@Override
	public BankWalletAccount deposit(String mobileNo, double depositAmount) throws BankException {
		em.getTransaction().begin();
		String str = "select a from BankWalletAccount a where a.phoneNum=?";
		TypedQuery<BankWalletAccount> query = em.createQuery(str, BankWalletAccount.class);
		query.setParameter(1, mobileNo);
		BankWalletAccount acc = query.getSingleResult();
		if (acc == null) {
			throw new BankException("Account does not exists");
		}
		double d = acc.getBalanceAmount() + depositAmount;
		acc.setBalanceAmount(d);
		// ac.setDate(Date.valueOf(LocalDate.now()));
		em.merge(acc);

		em.getTransaction().commit();
		return acc;
	}

	@Override
	public BankWalletAccount withdraw(String mobileNo, double depositAmount) throws BankException {
		em.getTransaction().begin();
		String str = "select a from BankWalletAccount a where a.phoneNum=?";
		TypedQuery<BankWalletAccount> query = em.createQuery(str, BankWalletAccount.class);
		query.setParameter(1, mobileNo);
		BankWalletAccount ac = query.getSingleResult();
		if (ac == null) {
			throw new BankException("does not exists");
		}
		double d = ac.getBalanceAmount() - depositAmount;
		ac.setBalanceAmount(d);
		// ac.setDate(Date.valueOf(LocalDate.now()));
		em.merge(ac);

		em.getTransaction().commit();
		return ac;
	}

	@Override
	public BankWalletAccount printTransactionDetails(String mobileNo) throws BankException {
		String str = "select a from BankWalletAccount a where a.phoneNum=?";
		TypedQuery<BankWalletAccount> query = em.createQuery(str, BankWalletAccount.class);
		query.setParameter(1, mobileNo);
		BankWalletAccount ac = query.getSingleResult();
		if (ac == null) {
			return ac;
		} else {
			throw new BankException("number doesnot exists");
		}
	}

}
