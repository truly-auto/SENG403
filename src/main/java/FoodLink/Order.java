package FoodLink;

import java.util.ArrayList;
import java.util.Random;


public class Order {
	private static Order orderInstance = null;
	private static int referenceNumber;
	private String List[] = {" "};
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private float cost;
	private database db = new database();
	
	public Order(){
		
		
		Random rand = new Random();
		referenceNumber = rand.nextInt(1000000);
		cost = 0;
	}
	
	public int getReferenceNumber()
	{
		return referenceNumber;
		
	}
	
	// dunno yet
	public void getOrder(){
		
		
	}
	public void viewInvoices(){
		
		
	}
	/**
	 * 
	 * This method updates order (add/remove items from order)
	 * @param quantity: the quantity read from order form
	 * @param supplierID: the id of supplier in database
	 * @param itemIndex: the item index in database
	 *
	 */
	public int updateOrder(int quantity, int supplierID, int itemIndex)
	{
		//System.out.println(db.getSupplierNames()[0]);
		
		//System.out.println("inventory!!!: " + db.getSupplierInventory(supplierID)[itemIndex][1].toString());
		// add item to order's itemList 
		boolean itemExists = false;
		for (int i = 0; i < itemList.size(); i++)
		{
			// check to see if item name is already in the itemList, if so update accordingly
			if (itemList.get(i).getItemName().equals(db.getSupplierInventory(supplierID)[itemIndex][1].toString())) {
				if (quantity != 0)
				{
					if(supplierHasItemAvailable(supplierID, itemIndex, quantity))
					{
						itemList.set(i, new Item(db.getSupplierInventory(supplierID)[itemIndex][1].toString(), Double.parseDouble(db.getSupplierInventory(supplierID)[itemIndex][4].toString()), quantity));
						
					}
					else
						return -1;
				}
				else {
					itemList.remove(i); // if item quantity was already in list and user is updating to 0, just remove it!
					
				}
				//System.out.println("QUANTITY BEING READ: " + quantity);
				itemExists = true;
			}
	
		}
		if (itemExists == false) {
			if (quantity != 0) {
				if(supplierHasItemAvailable(supplierID, itemIndex, quantity))
				{
								itemList.add(new Item(db.getSupplierInventory(supplierID)[itemIndex][1].toString(), Double.parseDouble(db.getSupplierInventory(supplierID)[itemIndex][4].toString()), quantity));
								
				}
				else 
					return -1;
			}
		}
		// zero out cost and recalculate new total after change
		cost = 0;
		for (int i = 0; i < itemList.size(); i++)
		{
			cost += (itemList.get(i).getItemPrice() * (float) itemList.get(i).getItemQuantity());
		}
		//debug code: uncomment to check order's accumulated cost
		//System.out.println("cost of order according to order: " + cost);
		return 0;
	}
	
	public boolean supplierHasItemAvailable(int supplierID, int itemIndex, int quantity)
	{
		if (Integer.parseInt(db.getSupplierInventory(supplierID)[itemIndex][3].toString()) >= quantity){
			return true;
		}
		else {
			return false;
		}
	}
	public Order getInvoices(){
		return orderInstance;
	}

	public void printInvoices(){

	}
}
