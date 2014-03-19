package FoodLink;
import FoodLink.gui.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		database test = new database();
		//test.addSupplier("'Saferway'", "'567-576-5567'", "'56 Nofrill Blvd'", "'Calgary'", "'thesaferway@safest.com'");
		//test.getSupplier();
		try {
			Login window = new Login();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
