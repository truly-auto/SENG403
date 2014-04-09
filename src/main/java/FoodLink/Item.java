package FoodLink;

public class Item {
	private int itemKey;
	private int itemQuantity;
	private double itemPrice;
	private String itemName;
	
	// pass this class name, price, and quantity to instantiate
	public Item (String name, double price, int quantity)
	{
		if (quantity < 0)
			throw new ArrayIndexOutOfBoundsException();
		if (price < 0)
		{
			throw new IllegalArgumentException("Price cannot be negative!");
		}
		itemName = name;
		itemPrice = price;
		itemQuantity = quantity;
		
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getItemQuantity() {
		return itemQuantity;
	}
	
	public double getItemPrice() {
		return itemPrice;
	}

	
	
	
}
