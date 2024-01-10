import java.sql.Date;

public class OrderData {
	private String nameString, iceString, sugarString, sizeString, toppingString, username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private int total, quantitiy;

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getIceString() {
		return iceString;
	}

	public void setIceString(String iceString) {
		this.iceString = iceString;
	}

	public String getSugarString() {
		return sugarString;
	}

	public void setSugarString(String sugarString) {
		this.sugarString = sugarString;
	}

	public String getSizeString() {
		return sizeString;
	}

	public void setSizeString(String sizeString) {
		this.sizeString = sizeString;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getQuantitiy() {
		return quantitiy;
	}

	public void setQuantitiy(int quantitiy) {
		this.quantitiy = quantitiy;
	}

	public String getToppingString() {
		return toppingString;
	}

	public void setToppingString(String toppingString) {
		this.toppingString = toppingString;
	}

	public OrderData(String name, String ice, String sugar, String size, int price, int amount, String topping,
			String user) {
		nameString = name;
		iceString = ice;
		sugarString = sugar;
		sizeString = size;
		total = price;
		quantitiy = amount;
		toppingString = topping;
		username = user;

	}

}