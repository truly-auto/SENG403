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
		
		Order o = new Order();
		o.updateOrder(4, 1, 0);
		o.updateOrder(0, 1, 0);
		assertEquals(o.getCost(), (double)0, DELTA);  
		
	}

	/**
	 * Test to ensure order price for multiple items calculated correctly
	 */
	@Test
	public void testCostTotal() {
		
		Order o = new Order();
		o.updateOrder(2, 1, 0);	// 22 apple pies = 4*2
		o.updateOrder(1, 1, 1);	// 1 Cinamon bb pies = 40*1
		o.updateOrder(0, 1, 2); // 0 of something else = 0
		assertEquals(o.getCost(), (double)48, DELTA);  
		
	}
}
