package account;

public class Transaction {

	public String date;
	public String time;
	public String amount;
	public String type;
	
	public Transaction(String date, String time, String amount, String type) {
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.type = type;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public String getAmount() {
		return this.amount;
	}
	
	public String getType() {
		return this.type;
	}
	
	
	
}
