package com.cg.dao;

public interface IQueryMapper {

	public String insert="insert into bankwalletaccount(custName,phoneNum,email,balanceAmount) values(?,?,?,?)";
	public String getBal="select balanceAmount from bankwalletaccount where phoneNum=?";
	public String GET_ALL_ACCOUNT_INFO = "SELECT * FROM bankwalletaccount";
	public String GET_ACC_MOBILE_NO = "SELECT phoneNum FROM bankwalletaccount";
	public String UPDATE_BALANCE_QUERY = "UPDATE bankwalletaccount SET balanceAmount=? WHERE phoneNum=?";
	public String getacc="select * from bankwalletaccount where phoneNum=?";

}
