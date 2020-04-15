package ProductOrdering.database;

import java.util.ArrayList;
import java.util.Date;

public class Order extends Record{
    private String order_id;
    private String customer_id;
    private double total;
    private String delivery_address;
    private Date order_date;
    private Customer customer;
    private ArrayList<OrderItem> orderedItems;

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

    public String getOrderID() {
        return order_id;
    }

    public String getCustomerName(){
        return customer.getCustomerName();
    }

    public String getCustomerPhone(){
        return this.customer.getPhone();
    }

    public void setCustomer(Customer cus){
        this.customer = cus;
    }

    // Get and Create OrderedItems
    public ArrayList<OrderItem> getOrderedItems() { return orderedItems; }

    private void createOrderItem(Product product, int quantity){

        orderedItems.add(new OrderItem(product, quantity));
    }

    // Private class to create an Ordered Item
    protected class OrderItem {
        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity){
            this.product = product;
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public Product getProduct() {
            return product;
        }
    }
}