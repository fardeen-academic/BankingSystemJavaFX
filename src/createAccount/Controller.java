package createAccount;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import application.Main;
import banking.Bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller implements Initializable {
	public Bank bank = Main.bank;
	
	@FXML Button submitButton;
	@FXML Button cancelButton;
	@FXML Button homeButton;
	@FXML Button newAccountButton;
	
	@FXML Text successMessage;
	
	@FXML
	private Label title;
	@FXML
	private ChoiceBox<String> newAccountType;
	
	private String[] accountTypes = {"Savings Account", "Current Account", "Student Account"};
	
	@FXML
	private TextField nameInput;
	@FXML
	private TextField nidInput;
	@FXML
	private TextField initialBalanceInput;
	@FXML
	private TextField withdrawLimitInput;
	//withdrawLimitINput will be used as Withdraw Limit or Trade License Number or student ID according to account type 

	@FXML
	private TextField institutionInput;
	
	@FXML
	private Label withdrawLimitLabel;
	
	@FXML
	private Label accountNum;
	
	public static String acc;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		newAccountType.getItems().addAll(accountTypes);
		setForm();
	}
	
	public void setForm() {
		newAccountType.setValue("Select One");
		nameInput.setDisable(true);		
		nidInput.setDisable(true);
		initialBalanceInput.setDisable(true);
		withdrawLimitInput.setDisable(true);
		institutionInput.setDisable(true);
		
		nameInput.setText("");
		nidInput.setText("");
		initialBalanceInput.setText("");
		withdrawLimitInput.setText("");
		institutionInput.setText("");
		
		successMessage.setVisible(false);
		accountNum.setVisible(false);
		homeButton.setVisible(false);
		newAccountButton.setVisible(false);
		newAccountType.setOnAction(this::setAccountType);
	}
	
	public void setAccountType(ActionEvent event){
		String getTitle = newAccountType.getValue();
		title.setText("Create New " + getTitle);
		nameInput.setDisable(false);
		nidInput.setDisable(false);
		initialBalanceInput.setDisable(false);
		withdrawLimitInput.setDisable(false);
		if(getTitle=="Savings Account") {
			System.out.println("Selected Savings");
			withdrawLimitLabel.setText("Withdrawal Limit");
			institutionInput.setDisable(true);
			submitButton.setOnAction(this::createSavingsAccount);
			
		}else if(getTitle=="Current Account") {
			System.out.println("Selected Current");
			withdrawLimitLabel.setText("Trade License");
			institutionInput.setDisable(true);
			submitButton.setOnAction(this::createCurrentAccount);
			
		}else if(getTitle=="Student Account") {
			System.out.println("Selected Student");
			withdrawLimitLabel.setText("Student ID");
			institutionInput.setDisable(false);	
			submitButton.setOnAction(this::createStudentAccount);
		}
	}
	
	public void createSavingsAccount(ActionEvent event){
		String memberName = nameInput.getText();
		String memberNid = nidInput.getText();
		double accountBalance = Integer.parseInt(initialBalanceInput.getText());
		double maxWithdrawLimit = Integer.parseInt(withdrawLimitInput.getText()) + 1;
		acc = bank.addAccount(memberName, memberNid, accountBalance, maxWithdrawLimit);
		accountNum.setText(acc);
        System.out.println(bank.getAccounts());
        try {
			confirmationPage(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createCurrentAccount(ActionEvent event){
		String memberName = nameInput.getText();
		String memberNid = nidInput.getText();
		double accountBalance = Integer.parseInt(initialBalanceInput.getText());
		String tradeLicenseNumber = withdrawLimitInput.getText();
		acc = bank.addAccount(memberName, memberNid, accountBalance, tradeLicenseNumber);
		accountNum.setText(acc);
        System.out.println(bank.getAccounts());
        try {
			confirmationPage(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createStudentAccount(ActionEvent event){
		String memberName = nameInput.getText();
		String memberNid = nidInput.getText();
		double accountBalance = Integer.parseInt(initialBalanceInput.getText());
		String studentID = withdrawLimitInput.getText();
		String institution = institutionInput.getText();
		acc = bank.addAccount(memberName, memberNid, accountBalance, institution, studentID);
		accountNum.setText(acc);
        System.out.println(bank.getAccounts());
        try {
			confirmationPage(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void switchToMainMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../menu/Main.fxml");
	}
	
	public void switchToEmployeeMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../employeeMenu/Employee.fxml");
	}
	
	public void switchCreateAccountMenu(ActionEvent event) throws IOException {
		App.main.switchPage("../CreateAccount/CreateAccount.fxml");
	}
	
	public void confirmationPage(ActionEvent event) throws IOException {
		setForm();
		successMessage.setVisible(true);
		accountNum.setVisible(true);
		homeButton.setVisible(true);
		newAccountButton.setVisible(true);
	}
	
	public void resetPage(ActionEvent event) {
		successMessage.setVisible(false);
		accountNum.setVisible(false);
		homeButton.setVisible(false);
		newAccountButton.setVisible(false);
	}
	
	
	
	
}
