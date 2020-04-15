package ProductOrdering.database;

import java.sql.*;

public abstract class Insert extends DatabaseUtil {

    // Inserts a customer into the database
    public static String customer (String customer_name, String customer_id,
                                        String contact, String customer_addr,
                                        String payment, String company_name) {

        if (customer_name.length() > 50 ||
                customer_id.length() > 9 ||
                contact.length() > 10 ||
                customer_addr.length() > 50 ||
                payment.length() > 10 ||
                company_name.length() > 50) {
            return "Error: Character length too long";
        }

        if (connection == null) {
            return "Error: Database not connected";
        }

        try {
            String insert = "INSERT INTO Product VALUES (" + customer_name + ", " + customer_id + ", " +
                    contact + ", " + customer_addr + ", " +
                    payment + ", " + company_name + ");";

            PreparedStatement prepsInsertProduct = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            if (!prepsInsertProduct.execute()) {
                return "Error: Customer record already exists or failed to insert";
            }
        } catch (Exception ex) {
            return ex.toString();
        }

        return "Success";
    }

    // Inserts a Product
    public static String product (String product_name, double price,
                                       String product_id, int stock,
                                       String supplier_addr) {
        if (product_name.length() > 50 ||
                product_id.length() > 9 ||
                supplier_addr.length() > 50) {
            return "Error: Character length too long";
        }

        if (connection == null) {
            connect();
        }

        try {
            String insert = "INSERT INTO Product VALUES ('" + product_name + "', " + price + ", " + product_id + ", " + stock + ", '" + supplier_addr + "')";
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            prepsInsertProduct.execute();
        } catch (SQLException ex) {
            return ex.toString();
        }
        disconnect();
        return "Success";
    }

    public static String order (String order_id, String customer_id,
                                          String product_id, String delivery_addr,
                                          Date created) {
        if (order_id.length() > 9 ||
                customer_id.length() > 9 ||
                product_id.length() > 9 ||
                delivery_addr.length() > 50) {
            return "Error: Character length too long";
        }

        try {
            String insert = "INSERT INTO Product VALUES (" + order_id + ", " + customer_id + ", " +
                    product_id + ", " + delivery_addr + ", " +
                    created + ");";
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            if (!prepsInsertProduct.execute()) {
                return "Error: Product record already exists or failed to insert";
            }
        } catch (SQLException ex) {
            return ex.toString();
        }

        if (connection == null) {
            connect();
        }
        disconnect();
        return "Success";
    }
}
