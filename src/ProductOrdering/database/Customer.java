package ProductOrdering.database;

public class Customer extends Record {
    private String customer_name,
    customer_id,
    contact,
    business_address,
    payment_info,
    company_name;

    public Customer(String customer_name, String customer_id,
                    String contact, String business_address,
                    String payment_info, String company_name){
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.contact = contact;
        this.business_address = business_address;
        this.payment_info = payment_info;
        this.company_name = company_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public String getContact() {
        return contact;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getPayment_info() {
        return payment_info;
    }
}
