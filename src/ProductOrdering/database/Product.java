package ProductOrdering.database;

public class Product extends Record {
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

    public double getItem_price() {
        return item_price;
    }

    public int getStock() {
        return stock;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getSupplier_address() {
        return supplier_address;
    }
}
