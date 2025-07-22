package com.app.serviceimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.app.model.Account;
import com.braindata.bankmanagement.service.Rbi;

public class Sbi implements Rbi{
	
	public void createTable()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b193", "root", "root");
			String query = "create table Account(accNo int, name varchar(45), mobNo varchar(45), adharNo varchar(45), gender varchar(45), age int, balance int)";
			Statement stm = con.createStatement();
			stm.execute(query);
			con.close();		
			System.out.println("Table created successfully..!");
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void createAccount() {
		
		Account ac = new Account();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your accNO:");
		int an = sc.nextInt();
		ac.setAccNo(an);
		
		
		System.out.println("Enter your name");
		String nm = sc.next();
		ac.setName(nm);
		
		System.out.println("Enter your mobile number");
		String mn = sc.next();
		ac.setMobNo(mn);
		
		System.out.println("Enter your aadhar number");
		String adn = sc.next();
		ac.setAdharNo(adn);
		
		System.out.println("Enter your Gender");
		String gen = sc.next();
		ac.setGender(gen);
		
		System.out.println("Enter your age");
		int age = sc.nextInt();
		ac.setAge(age);
		
		System.out.println("Enter Balance");
		double bal = sc.nextDouble();
		ac.setBalance(bal);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b193", "root", "root");
			String query  = "insert into account values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, ac.getAccNo());
			ps.setString(2, ac.getName());
			ps.setString(3, ac.getMobNo());
			ps.setString(4, ac.getAdharNo());
			ps.setString(5, ac.getGender());
			ps.setInt(6, ac.getAge());
			ps.setDouble(7, ac.getBalance());
			
			ps.execute();
			con.close();
			
			System.out.println("Account created successfully");	
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public void checkBalance()
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your account number");
		int acn = sc.nextInt();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b193", "root", "root");
			String query  =  "select * from account where accno = "+acn;
			
			Statement smt = con.createStatement();
			
			ResultSet set = smt.executeQuery(query);
			
			while(set.next())
			{
			    int bal = set.getInt(7);
			    System.out.println(bal);
			}
			
			con.close();
			
			
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	@Override
	public void displayAllDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositeMoney() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your account number to add balance:");
		int acn = sc.nextInt();
		System.out.println("Enter Your amount to deposit:");
		int amt = sc.nextInt();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b193", "root", "root");
			String query  =  "select * from account where accno = "+acn;
			Statement smt = con.createStatement();
			
			ResultSet set = smt.executeQuery(query);
			
			if(set.next())
			{
			    int bal = set.getInt(7)+amt;
			    System.out.println("updated balance is:"+bal);
			    String updatequery  =  "update account set balance="+bal+"  where accno = "+acn;
			    smt.executeUpdate(updatequery);
			}
			else
			{
				System.out.println("Invalid Acc No.");
			}
			con.close();
	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void withdrawal() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your account number to withdraw money:");
		int acn = sc.nextInt();
		System.out.println("Enter Your amount to withdraw:");
		int withdrwamt = sc.nextInt();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b193", "root", "root");
			String query  =  "select * from account where accno = "+acn;
			Statement smt = con.createStatement();
			
			ResultSet set = smt.executeQuery(query);
			
			if(set.next())
			{
				
			    int bal = set.getInt(7)-withdrwamt;
			    if(bal>withdrwamt)
			    {
			    	 System.out.println("After withdrawing amount, your updated balance is:"+bal);
					    String updatequery  =  "update account set balance="+bal+"  where accno = "+acn;
					    smt.executeUpdate(updatequery);
					    System.out.println("Your amount is successfully withdrawan");
					    //con.close();
			    }
			    else
			    {
			    	System.out.println("Insufficient Balance...!!");
			    }
			   
			}
			con.close();
			
	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void balanceCheck() {
		// TODO Auto-generated method stub
		
	}
}
