package accountHolderMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AccountHolderMenu implements Initializable{
	
	@FXML Label accountHolderName;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		accountHolderName.setText(account.Account.getAccountName());
	}
	
	public void switchToAccountDetails(ActionEvent event) throws IOException {
		App.main.switchPage("../account/AccountDetails.fxml");
	}
	
	public void switchToTransactionDetails(ActionEvent event) throws IOException {
		App.main.switchPage("../account/TransactionDetails.fxml");
	}
	
	public void switchToTransferMoney(ActionEvent event) throws IOException {
		App.main.switchPage("../accountHolderMenu/Transfer.fxml");
	}
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../menu/Main.fxml");
	}
	
	public void logOut(ActionEvent event) throws IOException {
		account.Account.setAccount(null);
		App.main.switchPage("../accountHolderMenu/AccountHolderLogin.fxml");
	}
	
	public void exit(ActionEvent event) throws FileNotFoundException, IOException {
		Main.bank.saveData();
		Platform.exit();
		System.exit(0);
	}
}
