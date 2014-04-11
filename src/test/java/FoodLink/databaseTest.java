package FoodLink;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class databaseTest extends DatabaseTestHelper {
	
	database data;
	
	@Before
	public void setup() {
		data = new database();
		//Clear the database, YAY!!
		this.clearDatabase();
	}
	
	@Test
	public void testGetInventory() {
		
		String command = "INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "supplier_id");
		
		String[] item = {"papples", "fruits", "5000", "2000", "lb", String.valueOf(newID)};
		data.manageItems(item, newID, true);
		data.manageItems(item, newID, true);
		data.manageItems(item, newID, true);
		data.manageItems(item, newID, true);
		data.manageItems(item, newID, true);
		data.manageItems(item, newID, true);
		
		//This is not an effective test, needs revision
		Object[][] inventory = data.getInventory(newID);
		
		assertEquals("Expected 6 items", 6, inventory.length);
	}

	@Test
	public void testGetSpecSupplier() {
		String command = "INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "supplier_id");
		
		String[] dbSupplier = data.getSpecSupplier(newID);
		String[] desiredSupplier = {"ACE Bakery","1-800-443-7929","1 Hafis Rd.","Toronto","www.acebakery.com"};
		assertArrayEquals("Supplier different from expected",desiredSupplier, dbSupplier);
	}
	
	@Test
	public void testGetSpecSupermarket() {
		String command = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "store_id");
		
		String[] dbSupplier = data.getSpecSupermarket(newID);
		String[] desiredSupplier = {"ACE Bakery","1-800-443-7929","1 Hafis Rd.","Toronto","www.acebakery.com"};
		assertArrayEquals("Supplier different from expected",desiredSupplier, dbSupplier);
	}
	
	@Test
	public void testGetNonExistingSupplier() {
		String[] dbSupplier = data.getSpecSupplier(180);
		
		assertArrayEquals("This supplier shouldn't exist", new String[5], dbSupplier); 
	}
	
	@Test
	public void testAddSupermarketItem() {
		String command = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "store_id");
		
		String[] item = {"papples", "fruits", "5000", "2000", "lb", String.valueOf(newID)};
		data.addSupermarketItem(item, newID);
		
		//if we get here then everything is fine
		assertTrue(true);
	}
	
	@Test
	public void testModifySupermarketItem() {
		
		String command = "INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "supplier_id");
		
		command = "INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,UNIT_PRICE, UNIT) VALUES " 
				+ "('Beets','Vegetable'," + String.valueOf(newID) + ", 1050, 10, '20LBS')";
		int itemID = this.insertAndReturnID(command, "item_number");
		
		String[] item = {"edit", "edit", String.valueOf(newID), "0", "0", "edit"};
		data.modifySupermarketItem(item, itemID);
		
		//if we get here then everything is fine
		assertTrue(true);
	}
	
	@Test
	public void testGetSupermarketInventory() {
		
		String command = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "store_id");
		
		String[] item = {"papples", "fruits", "5000", "2000", "lb", String.valueOf(newID)};
		data.addSupermarketItem(item, newID);
		data.addSupermarketItem(item, newID);
		
		
		Object[][] inventory = data.getSupermarketInventory(newID);
		
		assertEquals("Expected 2 items", 2, inventory.length);
	}
	
	@Test
	public void testAddComment() {
		
		String command = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int store_id = this.insertAndReturnID(command, "store_id");
		
		String command2 = "INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int supplier_id = this.insertAndReturnID(command2, "supplier_id");
		
		data.addComment("good stuff!?", store_id, supplier_id);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetUserSupplier() {
		
		String command2 = "INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int supplier_id = this.insertAndReturnID(command2, "supplier_id");
		
		String command = "INSERT INTO SUPPLIER_USERS (USERNAME, PASSWORD, SUPPLIER_ID, MANAGER) VALUES "
				+ "('John_Doe','password', " + supplier_id + ", 'true')";
		this.executeCommand(command);
		
		Object[][] users = data.getUser(supplier_id, false);
		
		assertEquals("Expected 1 user", 1, users.length);
	}
	
	@Test
	public void testGetUserSupermarket() {
		
		String command2 = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int store_id = this.insertAndReturnID(command2, "store_id");
		
		String command = "INSERT INTO STORE_USERS (USERNAME, PASSWORD, STORE_ID, MANAGER) VALUES "
				+ "('John_Doe','password', " + store_id + ", 'false')";
		this.executeCommand(command);
		
		Object[][] users = data.getUser(store_id, true);
		
		assertEquals("Expected 1 user", 1, users.length);
	}
	
	@Test
	public void testAddAutomaticOrder(){
		
		String command4 = "INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int supplier_id = this.insertAndReturnID(command4, "supplier_id");
		
		String command = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "store_id");
		
		String command3 = "INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,UNIT_PRICE, UNIT) VALUES " 
				+ "('Beets','Vegetable'," + String.valueOf(supplier_id) + ", 1050, 10, '20LBS')";
		int itemID = this.insertAndReturnID(command3, "item_number");
		
		String command2 = "INSERT INTO supermarket_inventory (name, inventory_type, supermarket_id, quantity, unit_price, unit, supplier_item_number) VALUES "
				+ "('grapes', 'fruits', " + String.valueOf(newID) + ", 5000, 2000, 'lb', "+ String.valueOf(itemID) +")";
		int inventoryID = this.insertAndReturnID(command2, "inventory_number");

		
		//threshold, quantity
		String[] order = {"5", "10"};
		data.addAutomaticOrder(order, inventoryID);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetThreshold() {
		String command4 = "INSERT INTO SUPPLIER (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int supplier_id = this.insertAndReturnID(command4, "supplier_id");
		
		String command = "INSERT INTO SUPERMARKET (NAME, PHONENUMBER, ADDRESS, CITY, EMAIL) VALUES "
				+ "('ACE Bakery','1-800-443-7929','1 Hafis Rd.','Toronto','www.acebakery.com')";
		int newID = this.insertAndReturnID(command, "store_id");
		
		String command3 = "INSERT INTO ITEMS (NAME, ITEM_TYPE, SUPPLIER_ID, QUANTITY,UNIT_PRICE, UNIT) VALUES " 
				+ "('Beets','Vegetable'," + String.valueOf(supplier_id) + ", 1050, 10, '20LBS')";
		int itemID = this.insertAndReturnID(command3, "item_number");
		
		String command2 = "INSERT INTO supermarket_inventory (name, inventory_type, supermarket_id, quantity, unit_price, unit, supplier_item_number) VALUES "
				+ "('grapes', 'fruits', " + String.valueOf(newID) + ", 5000, 2000, 'lb', "+ String.valueOf(itemID) +")";
		int inventoryID = this.insertAndReturnID(command2, "inventory_number");
		
		int threshold = 5;
		
		//threshold, quantity
		String[] order = {""+5, "10"};
		data.addAutomaticOrder(order, inventoryID);
		
		Integer[] thresholdArray = data.getThreshold(inventoryID);
		
		assertEquals("threshold unnexpected value", threshold, (int) thresholdArray[0]);
	}
	
	public String[] copyToString(Object[] array) {
		String[] stringArray = new String[array.length];
		
		for (int i = 0; i < array.length; i++) {
			stringArray[i] = array[i].toString();
		}
		
		return stringArray;
	}
}
