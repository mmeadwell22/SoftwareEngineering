package ProductOrdering.database;

import java.sql.*;

public abstract class Insert extends DatabaseUtil {

    // Inserts a customer into the database
    public static void customer(String customer_name, String customer_id, String contact, String customer_addr, String payment, String company_name) {

        // If the incoming parameters do NOT match the constraints, print error
        System.out.println(customer_name.length());
        System.out.println(customer_id.length());
        System.out.println(contact.length());
        System.out.println(customer_addr.length());
        System.out.println(payment.length());
        System.out.println(company_name.length());
        if (customer_name.length() > 25 || customer_id.length() > 9 || contact.length() > 10 || customer_addr.length() > 50 || payment.length() != 16 || company_name.length() > 50) {
            System.out.println("Customer parameters dont match constraints.");
            return;
        }

        // Create Insert Query
        String insertQ = "INSERT INTO Customer VALUES ('" +
                customer_name + "', '" + customer_id + "', '" +
                contact + "', '" + customer_addr + "', '" +
                payment + "', '" + company_name + "');";

        try{
            connect();
            update(insertQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    // Inserts a Product into the Database
    public static void product(String product_name, double price, String product_id, int stock, String supplier_addr) {

        // If the incoming parameters do NOT match the constraints, print error
        if (product_name.length() > 50 ||
                price < 0 ||
                product_id.length() != 9 ||
                stock < 0 ||
                supplier_addr.length() > 50) {
            System.out.println("Product parameters dont match constraints.");
            return;
        }

        // Create Insert Query
        String insertQ = "INSERT INTO Product VALUES ('" +
                product_name + "', " + price + ", '" +
                product_id + "', " + stock + ", '" +
                supplier_addr + "');";

        try{
            connect();
            update(insertQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void order(String order_id, double total_price, String customer_id, String delivery_addr, Date created) {

        // If the incoming parameters do NOT match the constraints, print error
        if (order_id.length() > 9 ||
                total_price < 0 ||
                customer_id.length() > 9 ||
                delivery_addr.length() > 50) {
            System.out.println("Order parameters dont match constraints.");
            return ;
        }

        customer_id = customer_id.trim();

        // Create Insert Query
        String insertQ = "INSERT INTO dbo.[Order] VALUES ('" +
                order_id + "', " + total_price + ", '" +
                customer_id + "', '" + delivery_addr + "', '" +
                created + "');";

        try{
            connect();
            update(insertQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void orderItem(String product_id, int quantity, String order_id){
        // If the incoming parameters do NOT match the constraints, print error
        if(product_id.length() > 9 || quantity < 0 || order_id.length() > 9){
            System.out.println("Ordered item parameters dont match constraints.");
            return;
        }
        product_id = product_id.trim();
        // Create Insert Query
        String insertQ = "INSERT INTO OrderedItem VALUES ('" +
                product_id + "', " + quantity + ", '" +
                order_id + "');";

        try{
            connect();
            update(insertQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }
}