package prog24178;

public class Item {
	private int id;
	private String item;
	private int quantity;
	private double price;

    //default constructor
	public Item() {
         this(0, "", 0, 0.0);
	}

	//Parameterized constructor
	public Item(int id, String item, int quantity, double price) {
		
		this.id = id;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}

	//getters and setters for private datafields
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", item=" + item + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
