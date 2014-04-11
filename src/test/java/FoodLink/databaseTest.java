package FoodLink;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class databaseTest {
	
	/* TODO A big todo for the database tests is to have the database be empty when generated and
	 * add all entries for testing in the relevant tests
	 */
	
	database data;
	
	@Before
	public void setup() {
		data = new database();
	}
	
	@Test
	public void testGetInventory() {
		//This is not an effective test, needs revision
		Object[][] inventory = data.getInventory(1);
		
		assertEquals("Expected 6 items", 6, inventory.length);
	}

	@Test
	public void testGetSpecSupplier() {
		String[] dbSupplier = data.getSpecSupplier(1);
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
		String[] item = {"papples", "fruits", "5000", "2000", "lb", "1"};
		data.addSupermarketItem(item, 1);
		
		//if we get here then everything is fine
		assertTrue(true);
	}
	
	@Test
	public void testModifySupermarketItem() {
		String[] item = {"edit", "edit", "1", "0", "0", "edit"};
		data.modifySupermarketItem(item, 1);
		
		//if we get here then everything is fine
		assertTrue(true);
	}
	
	@Test
	public void testGetSupermarketInventory() {
		//This is not an effective test, needs revision
		Object[][] inventory = data.getSupermarketInventory(1);
		
		assertEquals("Expected 2 items", 2, inventory.length);
	}
	
	@Test
	public void testGetStoreName() {
		//This is not an effective test, needs revision
		String expectedName = "Jane Tops";
		
		String actualName = data.getStoreName(1);
		
		assertEquals(expectedName, actualName);
	}
	
	@Test
	public void testGetOrderItems() {
		
		Object [] [] expectedData = {{"Unsalted Potato Chips", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"Salted Potato Chips", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"Rippled Potato Chips", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"BBQ Spiral Snacks in a Bag", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"Garlic Onion Ring Snacks in a Bag", "Potato Chips and Snack", "0", "8", "20LBS", "0"}};
		
		Object [] [] actualData = data.getOrderItems(1);
		
		
		assertArrayEquals(expectedData, actualData);
	}
	
	@Test
	public void testUpdateOrderStatus()
	{
		boolean flag = data.updateOrderStatus("Complete", 2);
		assertTrue(flag);
	}
	
	@Test
	//Test if the Order Status table is empty or not. 
	//In this case, the table should always have 2 pre-populated items in them. 
	public void testGetLastElementInOrderHistory()
	{
		int actualData = data.getLastElementInOrderHistory();
		assertNotEquals(0, actualData);
	}
	
	@Test
	public void testAddToOrderHistory()
	{
		boolean flag = data.addToOrderHistory("Ammmazing Donuts", "Jane Tops", 1234.0, "Shipped", 1, 3);
		assertTrue(flag);
	}
	
	@Test
	//This is to test if the method addOrderInformation is executing properly
	public void testAddOrderInformation()
	{
		boolean flag = data.addOrderInformation(2, "Cream Puffs", "Bakery", 10, 8, "10LBS", 80.0, 80.0);
		assertTrue(flag);
	}
	
	@Test
	public void testGetOrderListSupplier() {
		
		Object [] [] expectedData = {{"Unsalted Potato Chips", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"Salted Potato Chips", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"Rippled Potato Chips", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"BBQ Spiral Snacks in a Bag", "Potato Chips and Snack", "0", "8", "20LBS", "0"}, {"Garlic Onion Ring Snacks in a Bag", "Potato Chips and Snack", "0", "8", "20LBS", "0"}};
		
		Object [] [] actualData = data.getOrderListSupplier(1);
		
		
		assertArrayEquals(expectedData, actualData);
	}
	
	
	public String[] copyToString(Object[] array) {
		String[] stringArray = new String[array.length];
		
		for (int i = 0; i < array.length; i++) {
			stringArray[i] = array[i].toString();
		}
		
		return stringArray;
	}
}
