package menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import application.App;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class Controller
{
	
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
	
	public void switchToAccountHolderMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../accountHolderMenu/AccountHolderLogin.fxml");
	}
	
	
	
	
	
	
}
