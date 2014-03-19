package FoodLink;

public class Inventory{
	
	database connect;
	
	public Inventory() {
		connect = new database();
	}
	
	public void addItem(Item item){
		
	}
	
	public void removeItem(int itemID){
		
	}
	
	public void editItem(String[] item, int itemID) {
		
		//quantity is item[2]
		checkThreshold(itemID, Integer.parseInt(item[2]));
		
		connect.modifySupermarketItem(item, itemID);
	}
	
	public void checkThreshold(int itemID, int quantity) {
		//get threshold from DB
		Integer[] thresholdArray = connect.getThreshold(itemID);
		//check if quantity < threshold
		if (thresholdArray != null && thresholdArray.length > 0 && quantity < thresholdArray[0]) {
			//if quantity too low then call an auto ordering thing
		}
	}
	
	public Item getItem(){
		//TODO 
		return null;
	}
	
	public void priceComparison(){
		
	}
}
