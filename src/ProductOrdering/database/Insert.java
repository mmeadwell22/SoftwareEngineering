package ProductOrdering.database;

import java.sql.*;

public abstract class Insert extends DatabaseUtil {

    // Inserts a customer into the database
    public static String customer(String customer_name, String customer_id,
                                  String contact, String customer_addr,
                                  String payment, String company_name) {

        // If the incoming parameters do NOT match the constraints, print error
        if (customer_name.length() > 25 ||
                customer_id.length() != 9 ||
                contact.length() > 10 ||
                customer_addr.length() > 50 ||
                payment.length() != 16 ||
                company_name.length() > 50) {
            return "Error: Character length too long";
        }

        // Create Insert Query
        String insert = "INSERT INTO Customer VALUES ('" +
                customer_name + "', '" + customer_id + "', '" +
                contact + "', '" + customer_addr + "', '" +
                payment + "', '" + company_name + "');";

        if (connection == null) {
            try {
                // Connect to the Database
                connect();
                // Create a Prepared Statement for INSERT Query
                PreparedStatement psInsertCustomer = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                // If the PreparedStatement doesn't execute, print error
                if (!psInsertCustomer.execute()) {
                    return "Error: Customer record already exists or failed to insert";
                }
            } catch (Exception ex) {
                // Print
                System.out.println(ex.toString());
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        }

        return "Success";
    }

    // Inserts a Product into the Database
    public static String product(String product_name, double price,
                                 String product_id, int stock,
                                 String supplier_addr) {

        // If the incoming parameters do NOT match the constraints, print error
        if (product_name.length() > 50 ||
                price < 0 ||
                product_id.length() != 9 ||
                stock < 0 ||
                supplier_addr.length() > 50) {
            return "Error: Character length too long";
        }

        // Create Insert Query
        String insert = "INSERT INTO Product VALUES ('" +
                product_name + "', " + price + ", '" +
                product_id + "', " + stock + ", '" +
                supplier_addr + "');";

        if (connection == null) {
            try {
                // Connect to the Database
                connect();
                // Create a Prepared Statement for INSERT Query
                PreparedStatement psInsertProduct = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                // If the PreparedStatement doesn't execute, print error
                if (!psInsertProduct.execute()) {
                    return "Error: Customer record already exists or failed to insert";
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        } else {
            // Disconnect from the DB and try to insert product again
            disconnect();
            product(product_name, price, product_id, stock, supplier_addr);
        }

        return "Success";
    }

    public static String order(String order_id, double total_price,
                               String customer_id, String delivery_addr,
                               Date created) {

        // If the incoming parameters do NOT match the constraints, print error
        if (order_id.length() != 9 ||
                total_price < 0 ||
                customer_id.length() != 9 ||
                delivery_addr.length() > 50) {
            return "Error: Character length too long";
        }

        // Create Insert Query
        String insert = "INSERT INTO [Order] VALUES ('" +
                order_id + "', " + total_price + ", '" +
                customer_id + "', '" + delivery_addr + "', '" +
                created + "');";

        if (connection == null) {
            try {
                // Connect to the Database
                connect();
                // Create a Prepared Statement for INSERT Query
                PreparedStatement psInsertOrder = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                // If the PreparedStatement doesn't execute, print error
                if (!psInsertOrder.execute()) {
                    return "Error: Product record already exists or failed to insert";
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            } finally {
                disconnect();
            }
        }else {
            disconnect();
            order(order_id, total_price, customer_id, delivery_addr, created);
        }

        return "Success";
    }
}
