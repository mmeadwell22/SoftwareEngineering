package ProductOrdering.database;

import java.util.ArrayList;
import java.util.Date;

public class Order{
    private String order_id;
    private String customer_id;
    private double total;
    private String delivery_address;
    private Date order_date;
    private Customer customer;

    // Constructor
    public Order (String order_id, double total,
                  String customer_id, String delivery_address,
                  Date order_date){
        this.order_id = order_id;
        this.total = total;
        this.customer_id = customer_id;
        this.delivery_address = delivery_address;
        this.order_date = order_date;
    }

    // Get and Set Properties
    public String getCustomerID() {
        return customer_id;
    }

    public String getDeliveryAddress() {
        return delivery_address;
    }

    public Date getOrderDate() {
        return order_date;
    }

    public double getTotal() {
        return total;
    }

    public String getOrderID() { return order_id; }

    public String getCustomerName(){
        return customer.getCustomerName();
    }

    public void setCustomer(Customer cus){
        this.customer = cus;
    }
}