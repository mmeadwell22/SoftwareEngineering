package ProductOrdering.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Abstract Class for Selecting Tuples in the Database
public abstract class Select extends DatabaseUtil {
    private static final String selectAllFrom = "SELECT * FROM ";

    // Returns an ArrayList of all Customer tuples in the database
    public static ArrayList<Customer> allCustomers() {
        ArrayList<Customer> customersArrayList = new ArrayList<>();
        ResultSet rs;
        String query = selectAllFrom + "Customer;";

        try {
            connect();
            rs = execute(query);
            // Populate the ArrayList with Customer Objects
            while (rs.next()) {
                customersArrayList.add(new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return populated Customer ArrayList
        return customersArrayList;
    }

    // Returns an ArrayList of all Order tuples in the database
    public static ArrayList<Order> allOrders() {
        ArrayList<Order> ordersArrayList = new ArrayList<>();
        ResultSet rs;
        String query = selectAllFrom + "dbo.[Order];";

        try {
            connect();
            rs = execute(query);
            // Populate the ArrayList with Order Objects
            while (rs.next()) {
                ordersArrayList.add(new Order(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5)
                ));
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersArrayList;
    }

/*    public static ArrayList<Order.OrderItem> orderedItemsList(String order_id){
        ArrayList<Order.OrderItem> orderItems = new ArrayList<>();
        ResultSet rs;

        String query = "SELECT * FROM OrderedItem WHERE Order_id = '" + order_id + "';";

        if ((rs = execute(query)) == null) {
            return null;
        }
        try{
            while(rs.next()){
                orderItems.add()
            }
        }

        return orderItems;
    }*/

    // Returns an ArrayList of all Products tuples in the database
    public static ArrayList<Product> allProducts() {
        ArrayList<Product> productsArrayList = new ArrayList<>();
        ResultSet rs;

        String query = selectAllFrom + "Product;";

        try {
            connect();
            rs = execute(query);
            // Populate the ArrayList with Product Objects
            while (rs.next()) {
                productsArrayList.add(new Product(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return populated Product ArrayList
        return productsArrayList;
    }

    // Returns a unique Customer Object based on the customer id
    public static Customer uniqueCustomer(String id) {
        // Create customer object
        Customer customer = null;
        ResultSet rs;
        // Trim the whitespace
        id = id.trim();
        String query = selectAllFrom + "dbo.Customer WHERE Customer_ID = '" + id + "';";

        try {
            connect();
            rs = execute(query);
            // Create Customer based on Unique ID
            while (rs.next()) {
                customer = new Customer(
                        rs.getString(1), // customer name
                        rs.getString(2), // customer ID
                        rs.getString(3), // contact
                        rs.getString(4), // business address
                        rs.getString(5), // payment info
                        rs.getString(6)  // company name
                );
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public static Order uniqueOrder(String id) {
        // Create order object
        Order order = null;
        ResultSet rs;

        String query = selectAllFrom + "dbo.[Order] WHERE Order_ID ='" + id + "';";

        try {
            connect();
            rs = execute(query);
            // Create Order based on Unique ID
            while (rs.next()) {
                order = new Order(
                        rs.getString(1), // order ID
                        rs.getDouble(2), // price
                        rs.getString(3), // customer ID
                        rs.getString(4), // delivery address
                        rs.getDate(5)    // Date
                );
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public static Product uniqueProduct(String id) {
        // Initialize product object
        Product product = null;
        ResultSet rs;

        String query = selectAllFrom + "Product WHERE Product_ID ='" + id + "';";

        // Create Product based on Unique ID
        try {
            connect();
            rs = execute(query);
            while (rs.next()) {
                product = new Product(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}

