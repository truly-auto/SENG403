package FoodLink;

import static org.junit.Assert.*;

import org.junit.Test;

public class testOrder {
	private static final double DELTA = 1e-15;
	/**
	 * Add more item than supplier has and ensure that it gives proper error code -1
	 */
	@Test
	public void testSystemDetectOutOfStock() {
		Order o = new Order();
		assertEquals(o.updateOrder(15000, 1, 1), -1);
	}
	/**
	 * Test to see it comes up with false for test out of stock
	 */
	@Test
	public void stockBooleanCheck() {
		Order o = new Order();
		assertEquals(o.supplierHasItemAvailable(1, 1, 15000), false);
		
	}
	/**
	 * Test to ensure that updates to orders will overwrite correctly when adding to original specified number
	 */
	@Test
	public void testOverwrite1() {
		Item i = new Item("Apple Pies", (float)4, 3);
		Order o = new Order();
		o.updateOrder(3, 1, 0);
		o.updateOrder(4, 1, 0);
		assertEquals(o.getCost(), (double)4*4, DELTA);  
		
	}
	/**
	 * Test to ensure that updates to orders will overwrite correctly when lowering the amount to 0
	 */
	@Test
	public void testOverwrite2() {
		Item i = new Item("Apple Pies", (float)4, 3);
		Order o = new Order();
		o.updateOrder(4, 1, 0);
		o.updateOrder(0, 1, 0);
		assertEquals(o.getCost(), (double)0, DELTA);  
		
	}

}
