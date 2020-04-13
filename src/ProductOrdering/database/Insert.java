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
            try {
                connect();
                String query = "INSERT INTO Customer VALUES ('" + customer_name + "', '" + customer_id + "', '" +
                        contact + "', '" + customer_addr + "', '" +
                        payment + "', '" + company_name + "');";

                PreparedStatement prepsInsertProduct = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                if (!prepsInsertProduct.execute()) {
                    return "Error: Customer record already exists or failed to insert";
                }
            } catch (Exception ex) {
                return ex.toString();
            }
        }
        else{
            disconnect();
            customer(customer_name, customer_id, contact, customer_addr, payment, company_name);
        }
        disconnect();
        return "Success";
    }

    // Inserts a Product
    public static String product (String product_name, double price,
                                       String product_id, int stock,
                                       String supplier_addr) throws SQLException {
        if (product_name.length() > 50 ||
                product_id.length() > 9 ||
                supplier_addr.length() > 50) {
            return "Error: Character length too long";
        }

        if (connection == null) {
            try {
                connect();
                String query = "INSERT INTO Product VALUES ('" + product_name + "', " + price + ", '" +
                        product_id + "', '" + stock + "', '" + supplier_addr + "');";
                PreparedStatement prepsInsertProduct = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                if (!prepsInsertProduct.execute()) {
                    return "Error: Product record already exists or failed to insert";
                }
            } catch (SQLException ex) {
                return ex.toString();
            }
        }
        else{
            disconnect();
            product(product_name, price, product_id, stock, supplier_addr);
        }
        disconnect();
        return "Success";
    }

    public static String order (String order_id, double price, String customer_id,
                                String delivery_addr, Date created) {
        if (order_id.length() > 9 ||
                customer_id.length() > 9 ||
                delivery_addr.length() > 50) {
            return "Error: Character length too long";
        }

        if(connection == null) {
            try {
                connect();
                String query = "INSERT INTO Order VALUES ('" + order_id + "', " + price + ", '" +
                        customer_id + "', '" + delivery_addr + "', " + created + ");";
                PreparedStatement prepsInsertProduct = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                if (!prepsInsertProduct.execute()) {
                    return "Error: Product record already exists or failed to insert";
                }
            } catch (SQLException ex) {
                return ex.toString();
            }
        }
        else {
            disconnect();
            order(order_id, price, customer_id, delivery_addr, created);
        }
        disconnect();
        return "Success";
    }
}
