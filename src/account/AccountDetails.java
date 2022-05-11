package account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import banking.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AccountDetails implements Initializable{
	BankAccount acc = account.Account.getAccount();
	@FXML Label accountNum;
	@FXML Label accountName;
	@FXML Label accountNid;
	@FXML Label totalBalance;
	@FXML Label availableBalance;
	@FXML Label maxWithdrawLimit;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		accountNum.setText(acc.getAccountNum());
		accountName.setText(acc.getMemberName());
		accountNid.setText(acc.getMemberNid());
		totalBalance.setText(acc.getAccountBalance()+"");
		double aB = acc.getAccountBalance()-acc.getMinBalance();
		availableBalance.setText(aB+"");
		maxWithdrawLimit.setText("N/A");
	}
	
	public void back(ActionEvent event) throws IOException {
		if(Account.getCameFrom()=="user") {
			App.main.switchPage("../accountHolderMenu/AccountHolderMenu.fxml");
		}else if(Account.getCameFrom()=="employee") {
			App.main.switchPage("../employeeMenu/Employee.fxml");
		}else {
			App.main.switchPage("../employeeMenu/AllAccountDetails.fxml");
		}
		
	}
	
	public void logOut(ActionEvent event) throws IOException {
		account.Account.setAccount(null);
		if(Account.getCameFrom()=="user") {
		App.main.switchPage("../accountHolderMenus/AccountHolderLogin.fxml");
		}else {
			App.main.switchPage("../menu/Main.fxml");
		}
	}	
}
