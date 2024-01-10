public class printdata {
	private static String menuname;
	private static double menuprice;
	private static String menucategory;

	public printdata(String menuname, double menuprice, String menucategory) {
		setMenuname(menuname);
		setMenuprice(menuprice);
		setMenucategory(menucategory);
	}

	public static String getMenuname() {
		return menuname;
	}

	public static void setMenuname(String menuname) {
		printdata.menuname = menuname;
	}

	public static double getMenuprice() {
		return menuprice;
	}

	public static void setMenuprice(double menuprice) {
		printdata.menuprice = menuprice;
	}

	public static String getMenucategory() {
		return menucategory;
	}

	public static void setMenucategory(String menucategory) {
		printdata.menucategory = menucategory;
	}

	public String display() {
		return getMenuname() + "\t" + getMenuprice() + "\t" + getMenucategory() + "\n";
	}
}