package FoodLink;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.Test;

import FoodLink.gui.SupermarketSys;
import FoodLink.gui.SupplierSys;
/**
 * 
 * THIS CODE DOES NOTHING YET :(
 * 
 *
 */
public class TestSupplierSys {


	
	/**
	 * Test for checking array is out of bounds
	 */
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testSupplierID()
	{
		SupplierSys s = new SupplierSys(9999, false);
		
	}
	
	/**
	 * Test for checking array is out of bounds
	 */
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testSupplierID2()
	{
		SupplierSys s = new SupplierSys(-1, false);
	}

}
