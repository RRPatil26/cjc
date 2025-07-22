package com.app.client;

import com.app.serviceimpl.Sbi;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 for createTable");
		System.out.println("Enter 2 for createAccount");
		System.out.println("Enter 3 for checkBalance");
		System.out.println("Enter 4 for displayAllDetails");
		System.out.println("Enter 5 for depositeMoney");
		System.out.println("Enter 6 for withdrawal");
		System.out.println("Enter 7 for balanceCheck");
		
		System.out.println("Enter Your choice:");
		int ch=sc.nextInt();
		Sbi sbi = new Sbi();
		
		switch(ch)
		{
		case 1:
			sbi.createTable();
			break;
		case 2:
			sbi.createAccount();
			break;
		case 3:
			sbi.checkBalance();
			break;
		case 4:
			sbi.displayAllDetails();
			break;
		case 5:
			sbi.depositeMoney();
			break;
		case 6:
			sbi.withdrawal();
			break;
		case 7:
			sbi.balanceCheck();
			break;
		default:
			System.out.println("Not right choice.....!!");
			break;
		
		}
//		sbi.createTable();
//		sbi.createAccount();
//		sbi.checkBalance();
//		sbi.depositeMoney();
		
	
		
		
	}

}
