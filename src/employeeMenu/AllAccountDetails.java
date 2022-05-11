package employeeMenu;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import account.Account;
import application.App;
import application.Main;
import banking.Bank;
import banking.InvalidAccountException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllAccountDetails implements Initializable{
	public Bank bank = Main.bank;
	ArrayList<banking.BankAccount> accList;
	
	ArrayList<Accounts> localAccList;
	
	@FXML TableView<Accounts> table;

	@FXML TableColumn<Accounts, String> indexCol;
	@FXML TableColumn<Accounts, String> numberCol;
	@FXML TableColumn<Accounts, String> nameCol;

	
	@FXML ChoiceBox<String> accountType;
	
	private String[] accountTypes = {"All Accounts", "Savings Account", "Current Account", "Student Account"};
	
	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent arg0) {
			loadData();
		}
	};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		accountType.setValue("Select One");
		accountType.getItems().addAll(accountTypes);
		accountType.setOnAction(event);
		
		table.setOnMouseClicked(event ->{
			if(table.getSelectionModel().getSelectedItem() != null) {
				String num = table.getSelectionModel().getSelectedItem().getNumber();
				try {
					Account.setAccount(Main.bank.findAccount(num));
					App.main.switchPage("../account/AccountDetails.fxml");
				} catch (InvalidAccountException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		indexCol.setCellValueFactory(new PropertyValueFactory<Accounts, String>("index"));
		numberCol.setCellValueFactory(new PropertyValueFactory<Accounts, String>("number"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Accounts, String>("name"));
		loadData();
	}
	
	public void back(ActionEvent event) throws IOException {
		App.main.switchPage("../employeeMenu/Employee.fxml");
	}
	
	public void logOut(ActionEvent event) throws IOException {
		App.main.switchPage("../menu/Main.fxml");
	}
	
	public void loadData() {
		final ObservableList<Accounts> data = FXCollections.observableArrayList();
		accList = bank.getAccounts();
		if (accountType.getValue()=="All Accounts") {
			System.out.println("Selected All");
			accList = bank.getAccounts();
		}else if (accountType.getValue()=="Savings Account") {
			accList = bank.getAccounts("Saving");
		}else if (accountType.getValue()=="Current Account") {
			accList = bank.getAccounts("Current");
		}else if (accountType.getValue()=="Student Account") {
			accList = bank.getAccounts("Student");
		}
		if(accList.size()>0) {
			for (int i=0; i<accList.size(); i++) {
				String tIndex = (i+1) + "";
				String tNumber = accList.get(i).getAccountNum();
				String tName = accList.get(i).getMemberName();
				
				data.add(new Accounts(tIndex, tNumber, tName));
			    
			}
		}
		table.setItems(data);
		
	}

}
