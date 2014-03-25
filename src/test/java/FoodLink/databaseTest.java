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
	
	public String[] copyToString(Object[] array) {
		String[] stringArray = new String[array.length];
		
		for (int i = 0; i < array.length; i++) {
			stringArray[i] = array[i].toString();
		}
		
		return stringArray;
	}
}
