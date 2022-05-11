package employeeMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import account.Account;
import banking.InvalidAccountException;
import application.App;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DetailFormController implements Initializable{
	@FXML Button cancelButton;
	@FXML Button submitButton;
	
	@FXML Label pageTitle;
	
	@FXML TextField accNumber;
	
	@FXML Label error;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showError(false);
		if (employeeMenu.EmployeeMenuController.accOrTrx =="acc") {
			pageTitle.setText("View Account Details");
			submitButton.setOnAction(arg01 -> {
				try {
					accDetails(arg01);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}else {
			pageTitle.setText("View Transaction Details");
			submitButton.setOnAction(arg01 -> {
				try {
					trxDetails(arg01);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		}
		
	}
	
	public void cancel(ActionEvent event) throws IOException{
		App.main.switchPage("../employeeMenu/Employee.fxml");
	}
	
	public void accDetails(ActionEvent event) throws IOException {
		setBankAcc();
		App.main.switchPage("../account/AccountDetails.fxml");
	}
		
	public void trxDetails(ActionEvent event) throws IOException {
		setBankAcc();
		App.main.switchPage("../account/TransactionDetails.fxml");
	}
	
	public void setBankAcc() {
		String acc = accNumber.getText();
		try {
			Account.setAccount(Main.bank.findAccount(acc));
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			error.setText(e.getMessage());
			showError(true);
		}
		account.Account.setCameFrom("employee");
	}

	public void showError(Boolean e) {
		error.setVisible(e);
		if(e) {
			accNumber.setText("");
		}
	}
	
	
	
}
