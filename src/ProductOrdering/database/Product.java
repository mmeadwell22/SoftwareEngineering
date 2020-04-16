package ProductOrdering.database;

public class Product extends Record{
    private String item_name;
    private double item_price;
    private String product_id;
    private int stock;
    private String supplier_address;

    public Product(String item_name, double item_price,
                   String product_id, int stock,
                   String supplier_address){
        this.item_name = item_name;
        this.item_price = item_price;
        this.product_id = product_id;
        this.stock = stock;
        this.supplier_address = supplier_address;
    }

    public String getItemName() {
        return item_name;
    }

    public double getItemPrice() {
        return item_price;
    }

    public String getProductID() {
        return product_id;
    }

    public int getStock() {
        return stock;
    }

    public String getAddress() {
        return supplier_address;
    }
}
