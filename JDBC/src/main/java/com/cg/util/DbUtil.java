package com.cg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.exception.BankException;

public class DbUtil {
public static Connection getConnection() throws BankException{
	String url="jdbc:mysql://localhost:3306/bank";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url,"root","root");
	}catch(ClassNotFoundException e){
		throw new BankException(e.getMessage());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new BankException(e.getMessage());
	}
}
}
