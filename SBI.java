package com.braindata.bankmanagement.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.braindata.bankmanagement.model.Account;
import com.braindata.bankmanagement.service.RBI;

public class SBI implements RBI {
	Scanner sc = new Scanner(System.in);
	LocalDateTime now;
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
	private Account acnts[];

	public SBI(Account[] a) {
		this.acnts = a;
	}

	@Override
	public void createAccount() {
		for (int i = 0; i < acnts.length; i++) {
			if (acnts[i] == null) {
				Account a = new Account();

				a.setAccNo(Account.getNextaccNo() + 1);
//		System.out.println("static"+a.getNextaccNo());
//		System.out.println("non static"+a.getAccNo());

				Account.setNextaccNo(Account.getNextaccNo() + 1);
//		System.out.println(a.getAccNo());

				// Enter Name
				System.out.println("Enter Your Name");
//				sc.nextLine();
				String n = validName();
				if (n == null) {
					System.out.println("you can not create account.You tried many attempts.");
					return;
				} else {
					a.setName(n);
				}

				// Enter MobileNumber
				System.out.println("Enter Mobile Number :without +91 ");
				String mn = validMn();
				if (mn == null) {
					System.out.println("you can not create account.You tried many attempts.");
					return;
				} else {
					a.setMobNo(mn);
				}
				// Enter Aadhar
				System.out.println("Enter your Adhar No :");
				String ad = validAd();
				if (ad == null) {
					System.out.println("you can not create account.You tried many attempts.");
					return;
				} else {
					a.setAdharNo(ad);
				}

				// Enter Gender
				System.out.println("Enter Gender of Candidate ");
				String g = validGender();
				if(g==null)
				{
					System.out.println("you can not create account.You tried many attempts.");
					return;
				}
				else
				{
					a.setGender(g);
				}

				// Enter Age
				System.out.println("Enter Age");
				int age = validAge();
				if(age==0)
				{
					System.out.println("you can not create account.You tried many attempts.");
					return;
				}
				else
				{
					a.setAge(age);
				}

				// Enter Balance
				System.out.println("Enter Amount You want to Deposit (above 500):");
				double amt = validAmt();
				if(amt==0)
				{
					System.out.println("you can not create account.You tried many attempts.");
					return;
				}
				else
				{
					a.setBalance(amt);
				}
				sc.nextLine();
//		if(acnts[i]==null)
//		{
				acnts[i] = a;
//		}

				System.out.println("Your Account Created Successfully.... and Generate Ac Number\n");

				System.out.println("\t\t\tYour Account Number is : " + a.getAccNo()
						+ "\n\nImportant Note:******keep notedown Account Number*******");

				break;
			}
		}

	}

//	@Override
	public void displayAllDetails() {
		while (true) {
			boolean found = false;
			System.out.println("Enter Mobile Number :");
			String acn = sc.next();
			for (int i = 0; i < acnts.length; i++) {
				try {
					if (acnts[i].getMobNo().equals(acn)) {
						System.out.println("Account Number : " + acnts[i].getAccNo());
						System.out.println("Name 		   : " + acnts[i].getName());
						System.out.println("Mo. Number	   : " + acnts[i].getMobNo());
//				System.out.println("Account Number : "+a.getAccNo());
						System.out.println("Adhar Number   : " + acnts[i].getAdharNo());
//				System.out.println("Account Number : "+a.getAccNo());
						if ("f".equalsIgnoreCase(acnts[i].getGender())
								|| "female".equalsIgnoreCase(acnts[i].getGender())) {
							System.out.println("Gender     : Female");
						} else if ("m".equalsIgnoreCase(acnts[i].getGender())
								|| "male".equalsIgnoreCase(acnts[i].getGender())) {
							System.out.println("Gender     : Male");
						} else {
							System.out.println("Gender     : Transgender");
						}
						System.out.println("Account Number : " + acnts[i].getAge());
						System.out.println("Balance : " + acnts[i].getBalance());
						found = true;
						break;
					}
				} catch (Exception e) {
					System.out.println();
					break;
				}
			}

			if (!found) {
				System.out.println("Mo. no. not Registered");
			} else {
				break;
			}
		}
	}

//	}
//
	@Override

	public void depositeMoney() {
		now = LocalDateTime.now();
		boolean found = false;
		System.out.println("Enter Accoun Number");
		int c=5;
		outer:
			
		while (c>0) {
			try {
			long ac = sc.nextLong();
			for (int i = 0; i < acnts.length; i++) {
				if (acnts[i].getAccNo() == ac) {
					System.out.println("Enter Amount:");
					int cc=5;
					while (cc>0) {
						double d = sc.nextDouble();
						if (d > 0) {
							acnts[i].setBalance(acnts[i].getBalance() + d);
							System.out.println("Your SBI Ac No : 3122XXXX" + String.valueOf(acnts[i].getAccNo() % 10000)
									+ "\nCredited Amount Rs. " + d + "\n Date and Time: " + now.format(fmt)
									+ "\nAvailable Balance : " + acnts[i].getBalance());
							System.out.println();
							found = true;
							break;
						} else {
							System.out.println("Enter Valid Amount");
							System.out.println("You have Only "+--cc+" Attempt ");
						}
					}
					break;
				}
			}
		}
			catch(NullPointerException e)
			{
				sc.nextLine();
			}
			if (!found) {
				System.out.println("Enter Valid Account Number : ");
				System.out.println("You have Only "+--c+" Attempt ");
			} else {
				break;
			}
		}
	}

//
	public void withdrawal() {
		now = LocalDateTime.now();
		boolean found = false;
		System.out.println("Enter Account Number : ");
		int cc=5;
		outer:
		while (cc>0) {
			try {
				String ac1 =sc.nextLine();
			if(ac1==null || ac1.length()==0)
			{
				System.out.println("Enter Valid Ac number :");
				if(cc!=0) {
				System.out.println("You have Only "+--cc+" Attempts");
				if(cc==0)
					{
					break;
					}
					}
			}
			Long ac=Long.parseLong(ac1);
		for (int i = 0; i < acnts.length; i++) {

				if (ac == acnts[i].getAccNo() ) {
					System.out.println("Enter Amount :");
					int c=5;
					while (c>0) {
						double w = sc.nextDouble();
						if (w > acnts[i].getBalance()) {
							System.out.println("Insufficient Funds....");
						} else {
							if (w > (acnts[i].getBalance() - 500)) {
								System.out.println("Please Maintain Account Balance.....");
								System.out.println("Ac Balance Should be above 500");
								System.out.println("You Can Withdraw Only "+(acnts[i].getBalance()-500));
							} else if (w > 0) {
								acnts[i].setBalance(acnts[i].getBalance() - w);
								System.out.println(
										"Your SBI Ac No : 3122XXXX".concat(String.valueOf(acnts[i].getAccNo() % 10000))
												+ "\nDebiited Amount Rs. " + w + "\n Date and Time: " + now.format(fmt)
												+ "\nAvailable Balance : " + acnts[i].getBalance());
								found = true;
								break;
							} else {
								System.out.println("Enter Valid Amount.");
								System.out.println("You have only "+--c+" Attempt");
								if(c==0)
								{
									break outer;
								}
							}
						}
					}
					break;
				}
			}
			}
			catch(Exception e)
			{
//				System.out.println("Enter Valid Ac number :");
//				System.out.println("You have Only "+--cc+" Attempts");
				sc.nextLine();
			}
			if (!found) {
				System.out.println("Enter Valid Ac number :");
				System.out.println("You have Only "+--cc+" Attempts");
			} else {
				break;
			}
		}
	}

//	@Override
//	public void balanceCheck() {
//		System.out.println("Enter Account Number : ");
//		while (true) {
//			if (a.getAccNo() == sc.nextLong()) {
//				System.out.println("Account Balance : " + a.getBalance());
//				break;
//			} else {
//				System.out.println("Enter Valid Account Number.....");
//			}
//		}
//
//	}
	public void balanceCheck() {
		System.out.println("Enter Account Number :");
		boolean found = false;
		while (true) {
			long ac = sc.nextLong();
			for (int i = 0; i < acnts.length; i++) {
				if (ac == acnts[i].getAccNo()) {
					System.out.println("Available Balance : " + acnts[i].getBalance());
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println("Enter Valid Account Number : ");
			} else {
				break;
			}
		}
	}

//	// Accont Creation Validation methods
	private double validAmt() {
		int c=5;
		while (c>0) {
			double amt = sc.nextDouble();
			if (amt >= 500) {
				return amt;
			} else {
				System.out.println("First Amount Should be above 500......");
				System.out.println("You have Only "+--c+" Attempt");
			}
		}
		return 0;
	}

	private int validAge() {
		int c = 5;
		while (c > 0) {
			int age = sc.nextInt();
			if (age >= 18) {
				return age;
			} else {
				System.out.println("Age Should be 18 above...try again");
				c--;
				if (c != 0)
					System.out.println("you have only " + c + " Attempt");
			}
		}
		return 0;
	}

	private String validAd() {

		int c = 5;
		boolean isValid = true;
		while (c > 0) {
			String ad = sc.next();
			if (ad.length() != 12) {
				System.out.println("Adhar Number should be 12 digit...Try Again");
				System.out.println("You Have only " + --c + " Attempt");
			} else {
				for (int i = 0; i < ad.length(); i++) {
					if (!Character.isDigit(ad.charAt(i))) {
						System.out.println("Not Allow Special Character or Lettrs :");
						System.out.println("You Have only " + --c + " Attempt");
						isValid = false;
					}
				}
				if (isValid) {
					return ad;
				}
			}
		}
		sc.nextLine();
		return null;
	}

	private String validGender() {
		int c = 5;
		while (c > 0) {
			String g = sc.next();
			if (g.equalsIgnoreCase("female") || g.equalsIgnoreCase("male") || g.equalsIgnoreCase("f")
					|| g.equalsIgnoreCase("m") || g.equalsIgnoreCase("t") || g.equalsIgnoreCase("transgender")) {
				return g;
			} else {
				System.out.println("Enter Valid Gender");
				System.out.println("You Have Only " + --c + " Attempt");
			}
		}
		return null;

	}

	private String validName() {
//		Scanner sc1 = new Scanner(System.in);
		int c = 5;
		while (c > 0) {
			boolean f = false;
//			sc.nextLine();
			String n = sc.nextLine();
			if ("".equals(n)) {
				System.out.println("Name CanNot be Empty: try again");
				c--;
				System.out.println("yov have only " + (c) + " Attempts");
			} else {
				for (int i = 0; i < n.length(); i++) {
					if(' '!=n.charAt(i)) {
					if (!Character.isLetter(n.charAt(i))) {

						System.out.println("Name content only Letters: try again");
						c--;
						if (c != 0)
							System.out.println("yov have only " + (c) + " Attempts");
						f = true;
						break;
					}
					}

				}
				if (!f) {
					return n;
				}

			}
		}
		sc.nextLine();
		return null;
	}

	private String validMn() {
		int c = 5;
		while (c > 0) {
			String mn = sc.nextLine();
			boolean isValid = true;
//			System.out.println(mn.);
			if (mn.length() != 10|| mn.isEmpty() || mn==null) {
				System.out.println("Mobile Number should be 10 digit try again..");
				c--;
				if (c != 0) 
				{
					System.out.println("You have only " + c + " Attempt");
				}
				isValid = false;
			} else {
				for (int i = 0; i < mn.length(); i++) {
					if (!Character.isDigit(mn.charAt(i))) {
						System.out.println("Not Allow special Character");
						c--;
						System.out.println("You have only " + c + " Attempt");

						isValid = false;
						break;
					}
				}
				if (isValid) {
//					sc.nextLine();
					return mn;
				}
			}
		}
		sc.nextLine();
		return null;
	}

//	private String validMn() {
//
//		String splChar = "`!@#$%^&*()_-=+|\\}]{[;:/><,.?ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//
//		while (true) {
//			String ad = sc.nextLine();
//			for(int i=0;i<acnts.length;i++) {
//			if(acnts[i]!=null)//!ad.equals(acnts[i].getMobNo())
//					{
//				if(ad.equals(acnts[i].getMobNo()))
//				{
//					System.out.println("Mo. No. Already registerd try again");
//					break;
//				}
//				boolean isValid = true;
//
//				for (int k = 0; k < splChar.length(); k++) {
//					for (int j = 0; j < ad.length(); j++) {
//						if (splChar.charAt(k) == ad.charAt(j)) {
//							isValid = false;
//							break;
//						}
//					}
//					if (!isValid) {
//						break;
//					}
//				}
//				if (isValid && ad.length() == 10) {
//					return ad;
//				} else {
//					System.out.println("Not Allow Special Character OR Letter in Adhar Number plese Try again : ");
//				}
//					}
//			else
//			{
//				boolean isValid = true;
//
//				for (int k = 0; k < splChar.length(); k++) {
//					for (int j = 0; j < ad.length(); j++) {
//						if (splChar.charAt(k) == ad.charAt(j)) {
//							isValid = false;
//							break;
//						}
//					}
//					if (!isValid) {
//						break;
//					}
//				}
//				if (isValid && ad.length() == 10) {
//					return ad;
//				} else {
//					System.out.println("Not Allow Special Character OR Letter in Mobile Number plese Try again : ");
//				}			}
//			}
//		}
//	}

	@Override
	public void updateAcdetails() {
		now = LocalDateTime.now();
		System.out.println("Enter Account Number : ");
		while (true) {
			long ac = sc.nextLong();
			boolean f = false;
			for (int i = 0; i < acnts.length; i++) {
				if (ac == acnts[i].getAccNo()) {
					System.out.println("Enter choice : ");
					while (true) {
						System.out.println("1.Update Mobile No.\n2.Update Name.\n3.Update Age.\n0. Exit from Update");
						System.out.println();

						int ch = sc.nextInt();
						switch (ch) {
						case 1: {
							updateMono(acnts[i], sc, now);
							f = true;
							break;
						}
						case 2: {
							System.out.println("Enter New Name:");
							sc.nextLine();
							String nm = sc.nextLine();
							acnts[i].setName(nm);
							System.out.println("Name Update Successfully.....for Ac No." + acnts[i].getAccNo()
									+ "On Date : " + now.format(fmt));
							f = true;
							break;
						}
						case 3: {
							System.out.println("Enter New Age:");
							acnts[i].setAge(sc.nextInt());
							System.out.println("Age Update Successfully.....for Ac No." + acnts[i].getAccNo()
									+ "On Date : " + now.format(fmt));
							f = true;
							break;

						}
						case 0: {
							System.out.println("UPdates Close :");
							f = true;
							return;
						}
						default:
							System.out.println("Select Correct Choice for updates.");

						}
//					break;

					}

				}
			}
			if (!f) {
				System.out.println("Accoun Not Found...");
			} else {
				break;
			}
		}

	}

	private void updateMono(Account a, Scanner sc, LocalDateTime now) {

		while (true) {
			boolean v = true;
			System.out.println("Enter Old Mo no.");
			String omn = sc.next();
			if (a.getMobNo().equals(omn)) {
				System.out.println("Enter New Number...");
				String nmn = sc.next();
				if (nmn.length() != 10) {
					System.out.println("Mobile number must be 10 digits.");
					continue;
				}
				for (int i = 0; i < nmn.length(); i++) {
					if (!Character.isDigit(nmn.charAt(i))) {
						v = false;
						break;
					}
				}

				if (!v) {
					System.out.println("Invalid Mobile no. try again.");
					continue;
				} else {
					a.setMobNo(nmn);
					System.out.println(
							"Update Mo. No. Successfully...of Ac." + a.getAccNo() + "On Date : " + now.format(fmt));
					break;
				}
			}
		}
	}
}
