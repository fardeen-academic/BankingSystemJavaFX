package accountHolderMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.Main;
import banking.BankAccount;
import banking.InvalidAccountException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccountHolderLogin implements Initializable{
	@FXML TextField accountNum;
	@FXML TextField nidNum;
	
	@FXML Button submitButton;
	@FXML Button cancelButton;
	
	@FXML Label warning;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setWarning(false);
	}
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../menu/Main.fxml");
	}
	
	public void switchToAccountHolderMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../accountHolderMenu/AccountHolderMenu.fxml");
	}
	
	
	
	public void login(ActionEvent event) {
		String accountN = accountNum.getText();
		String nid = nidNum.getText();
		try {
			BankAccount acc = Main.bank.findAccount(nid, accountN);
			account.Account.setAccount(acc);	
			account.Account.setCameFrom("user");
			switchToAccountHolderMenu(event);
		} catch (InvalidAccountException e) {
			showError(e.getMessage());
		} catch (IOException e) {
			showError(e.getMessage());
		}
	}
	
	public void resetPage(ActionEvent event){
		setWarning(false);
		accountNum.setText("");
		nidNum.setText("");
	}
	
	
	void setWarning(boolean visibility) {
		warning.setVisible(visibility);
	}
	
	void showError(String error) {
		warning.setText(error);
		setWarning(true);
	}
}
