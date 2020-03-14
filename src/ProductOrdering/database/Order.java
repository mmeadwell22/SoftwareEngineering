package ProductOrdering.database;

import java.util.Date;

public class Order extends Record {
    private String order_id;
    private String customer_id;
    private String product_id;
    private double price;
    private String delivery_address;
    private Date order_date;

    public Order (String order_id, double price,
                  String customer_id, String product_id,
                  String delivery_address){
        this.order_id = order_id;
        this.price = price;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.delivery_address = delivery_address;
        order_date = new Date();
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public double getPrice() {
        return price;
    }

    public String getOrder_id() {
        return order_id;
    }
}
