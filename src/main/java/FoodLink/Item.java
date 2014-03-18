package FoodLink;

public class Item {
	private int itemKey;
	private int itemQuantity;
	private double itemPrice;
	private String itemName;

	public Item(){
		
	}
	
	public Item(int itemKey){
		setItemKey(itemKey);
	}
	
	public Item(String itemName){
		setItemName(itemName);
	}
	
	public Item(String itemName, double itemPrice){
		setItemName(itemName);
		setItemPrice(itemPrice);
	}
	
	public int getItemKey() {
		return itemKey;
	}
	public void setItemKey(int itemKey) {
		this.itemKey = itemKey;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}		
}
