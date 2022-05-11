package transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.Main;
import banking.Bank;
import banking.InvalidAccountException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class DepositController  implements Initializable {
public Bank bank = Main.bank;

	@FXML TextField accountNum;
	@FXML TextField amount;
	
	@FXML Button submitButton;
	@FXML Button cancelButton;
	@FXML Button backButton;
	@FXML Button newDepositButton;
	
	@FXML Label messageLabel1;
	@FXML Label amountLabel;
	@FXML Label messageLabel2;
	@FXML Label accNumLabel;
	
	@FXML Label warning;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setLabelVisibility(false);
		setWarning(false);
	}
	
	public void switchToEmployeeMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../employeeMenu/Employee.fxml");
	}
	
	public void deposit(ActionEvent event) {
		String accountToDeposit = accountNum.getText();
		double amountToDeposit = Double.parseDouble(amount.getText());
		accNumLabel.setText(accountToDeposit);
		amountLabel.setText(amount.getText());
		try {
			bank.deposit(accountToDeposit, amountToDeposit);
			setLabelVisibility(true);
			setWarning(false);
			System.out.println(bank.getAccounts());
			System.out.println(bank.getAccTransactions(accountToDeposit));
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			String warningText = e.getMessage();
			warning.setText(warningText);
			setLabelVisibility(false);
			setWarning(true);
			System.out.println(warningText);
		}
		
	}
	
	public void resetPage(ActionEvent event){
		setLabelVisibility(false);
		setWarning(false);
		accountNum.setText("");
		amount.setText("");
	}
	
	
	void setLabelVisibility(boolean visibility) {
		messageLabel1.setVisible(visibility);
		messageLabel2.setVisible(visibility);
		amountLabel.setVisible(visibility);
		accNumLabel.setVisible(visibility);
		backButton.setVisible(visibility);
		newDepositButton.setVisible(visibility);
	}
	
	void setWarning(boolean visibility) {
		warning.setVisible(visibility);
	}
}
