//Method for storing Logged in user details

package account;

import java.util.ArrayList;

import application.Main;
import banking.Bank;
import banking.BankAccount;
import banking.Transaction;

public class Account {
	public Bank bank = Main.bank;
	private static BankAccount account;
	private static String cameFrom;
	
	public static void setAccount(BankAccount acc) {
		account = acc;
	}
	
	public static BankAccount getAccount() {
		return account;
	}
	
	public static String getAccountName() {
		return account.getMemberName();
	}
	
	public static double getAccountBalance() {
		return account.getAccountBalance();
	}
	
	public static String getAccountNumber() {
		return account.getAccountNum();
	}
	
	public static String getAccountNid() {
		return account.getMemberNid();
	}
	
	public static double getMinBalance() {
		return account.getMinBalance();
	}
	
	public static ArrayList<Transaction> getTransactions() {
		return account.getTransactions();
	}
	
	public static String getCameFrom() {
		return cameFrom;
	}
	
	public static void setCameFrom(String from) {
		cameFrom = from;
	}
	
	
	public static void logout() {
		setAccount(null);
		setCameFrom("");
	}
}
