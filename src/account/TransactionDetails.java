package account;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.App;
import application.Main;
import banking.Bank;
import banking.InvalidAccountException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionDetails implements Initializable{
	public Bank bank = Main.bank;
	ArrayList<banking.Transaction> trxList;
	
	ArrayList<Transaction> localTrxList;
	
	@FXML TableView<Transaction> table;

	@FXML TableColumn<Transaction, String> dateCol;
	@FXML TableColumn<Transaction, String> timeCol;
	@FXML TableColumn<Transaction, String> amountCol;
	@FXML TableColumn<Transaction, String> typeCol;
	
	@FXML Label accName;
	@FXML Label accNum;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		accName.setText(Account.getAccountName());
		accNum.setText(Account.getAccountNumber());
		
		dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
		timeCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("time"));
		amountCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
		typeCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type"));
		final ObservableList<Transaction> data = FXCollections.observableArrayList();
		try {
			trxList = bank.getAccTransactions(Account.getAccountNumber());
			for (int i=0; i<trxList.size(); i++) {
				//Regex
				String trx = trxList.get(i).toString();
				Pattern p = Pattern.compile("([0-9-]*)[ ]([0-9:]*)[\t]([0-9.]*)[\t][\t]([a-zA-Z]*)");
		        Matcher m = p.matcher(trx);
		        
		        if (m.matches()) {
		            System.out.println("Match found");
		            String tDate = m.group(1);
		            String tTime = m.group(2);
		            String tAmount = m.group(3);
		            String tType = m.group(4);
		            System.out.println("Date: " + m.group(1));
		            System.out.println("Time: " + m.group(2));
		            System.out.println("Amount: " + m.group(3));
		            System.out.println("Type: " + m.group(4));
		            data.add(new Transaction(tDate, tTime, tAmount, tType));
		        } else {
		            System.out.println("No match found");
		        }
		        table.setItems(data);
			}
		} catch (InvalidAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void back(ActionEvent event) throws IOException {
		if(Account.getCameFrom()=="user") {
			App.main.switchPage("../accountHolderMenu/AccountHolderMenu.fxml");
		}else {
			App.main.switchPage("../employeeMenu/Employee.fxml");
		}
		
	}
	
	public void logOut(ActionEvent event) throws IOException {
		account.Account.setAccount(null);
		if(Account.getCameFrom()=="user") {
			Account.setCameFrom("");
			App.main.switchPage("../accountHolderMenus/AccountHolderLogin.fxml");
		}else {
			App.main.switchPage("../menu/Main.fxml");
		}
	}
	
	

}
