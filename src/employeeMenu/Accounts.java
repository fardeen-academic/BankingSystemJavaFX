package employeeMenu;

public class Accounts {
	public String index;
	public String number;
	public String name;
	
	public Accounts(String index, String number, String name) {
		this.index = index;
		this.number = number;
		this.name = name;
	}
	
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.number = index;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
