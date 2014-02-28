package FoodLink;

import static org.junit.Assert.*;

import org.junit.Test;

public class databaseTest {
	
	/* TODO A big todo for the database tests is to have the database be empty when generated and
	 * add all entries for testing in the relevant tests
	 */

	@Test
	public void testGetInventory() {
		//This is not an effective test, needs revision
		database data = new database();
		Object[][] inventory = data.getInventory(1);
		
		assertEquals("Expected 6 items", 6, inventory.length);
	}

	@Test
	public void testGetSpecSupplier() {
		database data = new database();
		String[] dbSupplier = data.getSpecSupplier(1);
		String[] desiredSupplier = {"ACE Bakery","1-800-443-7929","1 Hafis Rd.","Toronto","www.acebakery.com"};
		assertArrayEquals("Supplier different from expected",desiredSupplier, dbSupplier);
	}
	
	@Test
	public void testGetNonExistingSupplier() {
		database data = new database();
		String[] dbSupplier = data.getSpecSupplier(180);
		
		assertArrayEquals("This supplier shouldn't exist", new String[5], dbSupplier); 
	}
}
