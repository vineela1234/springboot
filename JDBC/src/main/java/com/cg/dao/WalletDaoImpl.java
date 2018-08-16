package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import com.cg.util.DbUtil;
import com.cg.bean.BankWalletAccount;
import com.cg.exception.BankException;

public class WalletDaoImpl implements IWalletDao {

	@Override
	public String createAccount(BankWalletAccount account) throws BankException {

		Connection con=DbUtil.getConnection();
		PreparedStatement stat;

		try {
			con.setAutoCommit(true);
			stat=con.prepareStatement(IQueryMapper.insert);

			stat.setString(1,account.getCustName());
			stat.setString(2,account.getPhoneNum());
			stat.setString(3,account.getEmail());
			stat.setDouble(4,account.getBalanceAmount() );
			int rs=stat.executeUpdate();

			if(rs>0){

				con.commit();
				return account.getPhoneNum();
			}else{
				throw new BankException("Mobile number cannot be added or used to create account");
			}
		} catch (SQLException e) {

			throw new BankException(e.getMessage());
		}		
	}

	@Override 
	public double showBalance(String mobileNo) throws BankException {

		Connection con=DbUtil.getConnection();
		PreparedStatement stat;
		try {
			stat=con.prepareStatement(IQueryMapper.getBal);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			con.commit();
			if(rs!=null){
				rs.next();
				return rs.getDouble("balanceAmount");
			}else{
				throw new BankException("Mobile number does not exists!");
			}
		} catch (SQLException e) {

			throw new BankException(e.getMessage());
		}
	}

	@Override
	public BankWalletAccount deposit(String mobileNo,double depositAmount)
			throws BankException {
		Connection con=DbUtil.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{

			stat=con.prepareStatement(IQueryMapper.getacc);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			con.commit();
			if(rs!=null){
				rs.next();
				BankWalletAccount acc=new BankWalletAccount();
				double balance=rs.getDouble("balanceAmount")+depositAmount;
				acc.setCustName(rs.getString("custName"));
				acc.setPhoneNum(rs.getString("phoneNum"));
				acc.setBalanceAmount(balance);
				acc.setEmail(rs.getString("email"));
				//	acc.setDate(""+ new java.util.Date());

				stat1=con.prepareStatement(IQueryMapper.UPDATE_BALANCE_QUERY);
				stat1.setDouble(1, acc.getBalanceAmount());
				//	stat1.setString(2, acc.getDate());
				stat1.setString(2, acc.getPhoneNum());
				int rs1=stat1.executeUpdate();

				if(rs1==1){

					con.commit();

					return acc;
				}else{
					throw new BankException("balance is not updated");
				}

			}
			else{
				throw new BankException("mobile no does not exists");
			}


		}catch (SQLException e) {

			throw new BankException(e.getMessage());
		}
	}

	@Override
	public BankWalletAccount withdraw(String mobileNo,double withdrawAmount) throws BankException {
		Connection con=DbUtil.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{

			stat=con.prepareStatement(IQueryMapper.getacc);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			if(rs!=null){
				rs.next();
				BankWalletAccount acc=new BankWalletAccount();
				double balance=rs.getDouble("balanceAmount")-withdrawAmount;
				acc.setCustName(rs.getString("custName"));
				acc.setPhoneNum(rs.getString("phoneNum"));
				acc.setBalanceAmount(balance);
				acc.setEmail(rs.getString("email"));
				//acc.setDate(Date.valueOf(LocalDate.now()));
				//acc.setDate(LocalDateTime.now());
				//Account acc1=new Account();

				stat1=con.prepareStatement(IQueryMapper.UPDATE_BALANCE_QUERY);
				stat1.setDouble(1, acc.getBalanceAmount());
				//stat1.setDate(2, acc.getDate());
				stat1.setString(2, acc.getPhoneNum());
				int rs1=stat1.executeUpdate();


				if(rs1==1){
					//rs1.next();
					con.commit();
					//bal1=acc.getBalance();
					return acc;
				}else{
					throw new BankException("mobile no does not exists");
				}
				//return bal1;
			}
			else{
				throw new BankException("mobile no does not exists");
			}


		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BankException(e.getMessage());
		}

	}

	@Override
	public BankWalletAccount printTransactionDetails(String mobileNo)
			throws BankException {
		BankWalletAccount account = getAccountDetails(mobileNo);
		return account;
	}

	@Override
	public BankWalletAccount getAccountDetails(String mobileNo)
			throws BankException {
		Connection con=DbUtil.getConnection();
		PreparedStatement stat;
		try{
			stat=con.prepareStatement(IQueryMapper.getacc);
			stat.setString(1, mobileNo);
			ResultSet rs=stat.executeQuery();
			con.commit();
			if(rs!=null){
				rs.next();
				BankWalletAccount ac=new BankWalletAccount();
				ac.setCustName(rs.getString("custName"));
				ac.setPhoneNum(rs.getString("phoneNum"));
				ac.setEmail(rs.getString("email"));
				ac.setBalanceAmount(rs.getDouble("balanceAmount"));

				return ac;
			}else{
				throw new BankException("mobile no does not exists");
			}


		}catch (SQLException e) {

			throw new BankException(e.getMessage());
		}

	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount)
			throws BankException {
		BankWalletAccount acc1 = getAccountDetails(mobile1);
		BankWalletAccount acc2 = getAccountDetails(mobile2);

		if(acc1 == null || acc2 == null)
			throw new BankException("Account doesnot exist. Amount can't be transferred");

		if(amount > acc1.getBalanceAmount())
			throw new BankException("Account balance is low");
		double bal = acc1.getBalanceAmount()-amount;			//withdraw from mobile1
		acc1.setBalanceAmount(bal);
		double bal1 = acc2.getBalanceAmount()+amount;
		acc2.setBalanceAmount(bal1);		//deposit in mobile2

		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement stat = connection.prepareStatement(IQueryMapper.UPDATE_BALANCE_QUERY);
			stat.setDouble(1, acc1.getBalanceAmount());
			stat.setString(2, acc1.getDate());
			stat.setString(3, mobile1);
			int res1 = stat.executeUpdate();

			PreparedStatement stat1 = connection.prepareStatement(IQueryMapper.UPDATE_BALANCE_QUERY);
			stat.setDouble(1, acc2.getBalanceAmount());
			stat.setString(2, acc2.getDate());
			stat.setString(3, mobile2);
			int res2 = stat1.executeUpdate();

		} catch (SQLException e) {

			throw new BankException(e.getMessage());
		}
		return true;
	}
}