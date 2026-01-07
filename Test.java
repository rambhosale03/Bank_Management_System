package com.braindata.bankmanagement.client;

import java.util.Scanner;

import com.braindata.bankmanagement.model.Account;
import com.braindata.bankmanagement.service.RBI;
import com.braindata.bankmanagement.serviceImpl.SBI;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("**************Welcome To SBI Bank**************\n");
		System.out.println("Enter Account Limitation of SBI Branch");
		int n = sc.nextInt();
		Account acnts[] = new Account[n];
		RBI s = new SBI(acnts);
		while (true) {
			System.out.println();
			System.out.println("******************************************");
			System.out.println("Desk 1 : Create New Account .");
			System.out.println("Desk 2 : Show Account Details.");
			System.out.println("Desk 3 : Deposit Money");
			System.out.println("Desk 4 : Wthdraw Money");
			System.out.println("Desk 5 : Balance Enquiery");
			System.out.println("Desk 6 : Update Account Details");
			System.out.println("Enter 0 for Exit Application");

			System.out.println("Enter Desk No:");
			int ch = sc.nextInt();

			switch (ch) {
			case 1: {
				s.createAccount();
				break;
			}
			case 2: {
				s.displayAllDetails();
				break;
			}
			case 3: {
				s.depositeMoney();
				break;
			}
			case 4: {
				s.withdrawal();
				break;
			}
			case 5: {
				s.balanceCheck();
				break;
			}
			case 0: {
				System.out.println("***************Thank You...***************");
				System.exit(0);
			}
			case 6: {
				s.updateAcdetails();
			}
			default:
				System.out.println("You Enterd Invalid Desk No..... Try again");
			}

		}
//		}
	}
}
