package ProductOrdering.database;

public class Customer extends Record {
    private String customer_name,
    customer_id,
    phone,
    business_address,
    payment_info,
    company_name;

    Order order;

    public Customer(String customer_name, String customer_id,
                    String phone, String business_address,
                    String payment_info, String company_name){
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.phone = phone;
        this.business_address = business_address;
        this.payment_info = payment_info;
        this.company_name = company_name;
    }

    public String getCompanyName() {
        return company_name;
    }

    public String getAddress() {
        return business_address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomerID() {
        return customer_id;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public String getPaymentInfo() {
        return payment_info;
    }

    //setter for setting the order/orders associated with the customer.
    public void setOrder(Order order){
        this.order = order;
    }
}
