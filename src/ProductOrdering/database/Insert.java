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
            return "Error: Character length too long or negative number.";
        }

        // Create Insert Query
        String insert = "INSERT INTO Customer VALUES ('" +
                customer_name + "', '" + customer_id + "', '" +
                contact + "', '" + customer_addr + "', '" +
                payment + "', '" + company_name + "');";

        if (execute(insert) == -1) {
            // Disconnect from the DB and try to insert product again
            disconnect();
            customer(customer_name, customer_id, contact, customer_addr,payment,company_name);
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
            return "Error: Character length too long or negative number.";
        }

        // Create Insert Query
        String insert = "INSERT INTO Product VALUES ('" +
                product_name + "', " + price + ", '" +
                product_id + "', " + stock + ", '" +
                supplier_addr + "');";

        if (execute(insert) == -1){
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
            return "Error: Character length too long or negative number.";
        }

        // Create Insert Query
        String insert = "INSERT INTO [Order] VALUES ('" +
                order_id + "', " + total_price + ", '" +
                customer_id + "', '" + delivery_addr + "', '" +
                created + "');";

        if (execute(insert) == -1) {
            // Disconnect from the DB and try to insert product again
            disconnect();
            order(order_id, total_price, customer_id, delivery_addr, created);
        }

        // Debugging
        return "Success";
    }

    public static void orderItem(String product_id, int quantity, String order_id){
        // If the incoming parameters do NOT match the constraints, print error
        if(product_id.length() != 9 || quantity < 0 || order_id.length() != 9){
            return;
        }

        // Create Insert Query
        String insertQuery = "INSERT INTO OrderedItem VALUES ('" +
                product_id + "', " + quantity + ", '" +
                order_id + "');";

        if (execute(insertQuery) == -1) {
            // Disconnect from the DB and try to insert product again
            disconnect();
            orderItem(product_id, quantity, order_id);
        }
    }

    private static int execute(String insertQuery){
        if(connection == null) {
            try {
                // Connect to the Database
                connect();
                // Create a Prepared Statement for INSERT Query
                PreparedStatement psInsert = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                // If the PreparedStatement doesn't execute, print error
                if (!psInsert.execute()) {
                    return -1;
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                return -1;
            } finally {
                disconnect();
            }
        } else { return -1; }
        return 0;
    }
}
