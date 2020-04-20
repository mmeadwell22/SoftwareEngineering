package ProductOrdering.database;

public class OrderDetails {
    private String itemName;
    private int itemQuantity;
    private double itemPrice;
    private String productID;
    private double total;

    public OrderDetails(String itemName, int itemQuantity, double itemPrice, String productID){
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.productID = productID;
        this.total = 0;
    }

    public String getItemName(){
        return this.itemName;
    }

    public int getItemQuantity(){
        return this.itemQuantity;
    }

    public double getItemPrice(){
        return this.itemPrice;
    }

    public String getProductID(){
        return this.productID;
    }

    public double getTotal(){
        return this.itemPrice * this.itemQuantity;
    }
}
