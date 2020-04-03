package ProductOrdering.database;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Order extends Record{
    private String order_id;
    private String customer_id;
    private double total;
    private SimpleStringProperty delivery_address;
    private Date order_date;

    public Order (String order_id, double total,
                  String customer_id, String delivery_address,
                  Date order_date){
        this.order_id = order_id;
        this.total = total;
        this.customer_id = customer_id;
        this.delivery_address = new SimpleStringProperty(delivery_address);
        this.order_date = order_date;
    }

    public String getCustomerID() {
        return customer_id;
    }

    public String GetShipAddress() {
        return delivery_address.get();
    }

    public Date getOrderDate() {
        return order_date;
    }

    public double getTotal() {
        return total;
    }

    public String getOrderID() {
        return order_id;
    }
}