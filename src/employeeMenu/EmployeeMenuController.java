package employeeMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import application.App;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class EmployeeMenuController {
	
	public static String accOrTrx;
	
	public void exit(ActionEvent event) throws FileNotFoundException, IOException {
		Main.bank.saveData();
		Platform.exit();
		System.exit(0);
	}
	
	//Scene Switching Methods
	//https://www.youtube.com/watch?v=hcM-R-YOKkQ
	
	public void switchToEmployeeMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../employeeMenu/Employee.fxml");
	}
	public void switchToMainMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../menu/Main.fxml");
	}
	public void switchToCreateAccountMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../createAccount/CreateAccount.fxml");
	}
	public void switchToDepositMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../transaction/Deposit.fxml");
	}
	public void switchToWithdrawMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../transaction/Withdraw.fxml");
	}
	public void switchToTranferMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../transaction/Transfer.fxml");
	}
	public void switchToAccountHolderMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../accountHolderMenu/AccountHolderLogin.fxml");
	}
	
	public void switchToAccountDetails(ActionEvent event) throws IOException {
		accOrTrx = "acc";
		App.main.switchPage("../employeeMenu/DetailForm.fxml");
	}
	
	public void switchToTransactionDetails(ActionEvent event) throws IOException {
		accOrTrx = "trx";
		App.main.switchPage("../employeeMenu/DetailForm.fxml");
	}
	
	public void switchToAllAccountDetails(ActionEvent event) throws IOException {
		App.main.switchPage("../employeeMenu/AllAccountDetails.fxml");
	}
	
}
