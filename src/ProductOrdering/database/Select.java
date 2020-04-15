package ProductOrdering.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Abstract Class for Selecting Tuples in the Database
public abstract class Select extends DatabaseUtil {

    // Returns an ArrayList of all Customer tuples in the database
    public static ArrayList<Customer> allCustomers() {
        ArrayList<Customer> customersArrayList = new ArrayList<>();
        if (connection == null) {
            try {
                // Connect to the Database
                connect();

                // Create Query, Statement, and ResultSet
                String query = "SELECT * from Customer";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

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
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Select.all() error.");
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        }
        else {
            // If the function did not work, try again
            disconnect();
            allCustomers();
        }

        // Return populated Customer ArrayList
        return customersArrayList;
    }

    // Returns an ArrayList of all Order tuples in the database
    public static ArrayList<Order> allOrders() {
        ArrayList<Order> ordersArrayList = new ArrayList<>();
        if (connection == null) {
            try {
                // Connect to the Database
                connect();

                // Create Query, Statement, and ResultSet
                String query = "SELECT * from  dbo.[Order]";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

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
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Select.all(table: String) error.");
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        }
        else {
            // If the function did not work, try again
            disconnect();
            allOrders();
        }

        return ordersArrayList;
    }

    // Returns an ArrayList of all Products tuples in the database
    public static ArrayList<Product> allProducts() {
        ArrayList<Product> productsArrayList = new ArrayList<>();
        if (connection == null) {
            try {
                // Connect to the Database
                connect();

                // Create Query, Statement, and ResultSet
                String query = "SELECT * from Product";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

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

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Select.all(table: String) error.");
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        }
        else {
            // If the function did not work, try again
            disconnect();
            allProducts();
        }

        // Return populated Product ArrayList
        return productsArrayList;
    }

    // Returns a unique Customer Object based on the customer id
    public static Customer uniqueCustomer(String id) {
        // Trim the whitespace
        id = id.trim();

        // Create customer object
        Customer customer = null;

        if (connection == null) {
            try {
                // Connect to the Database
                connect();

                // Create Query, Statement, and ResultSet
                String query = "SELECT * FROM dbo.Customer WHERE Customer_ID = '" + id + "';";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                // Create Customer based on Unique ID
                while(rs.next()) {
                    customer = new Customer(
                            rs.getString(1), // customer name
                            rs.getString(2), // customer ID
                            rs.getString(3), // contact
                            rs.getString(4), // business address
                            rs.getString(5), // payment info
                            rs.getString(6)  // company name
                    );
                }
            } catch (SQLException ex) {
                System.out.println("Select.uniqueCustomer(id: String) error.");
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        }
        return customer;
    }

    public static Order uniqueOrder(String id) {
        // Create order object
        Order order = null;

        if (connection == null) {
            try {
                // Connect to the Database
                connect();

                // Create Query, Statement, and ResultSet
                String query = "SELECT * from dbo.[Order] WHERE Order_ID ='" + id +"';";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                // Create Order based on Unique ID
                while(rs.next()) {
                    order = new Order(
                            rs.getString(1), // order ID
                            rs.getDouble(2), // price
                            rs.getString(3), // customer ID
                            rs.getString(4), // delivery address
                            rs.getDate(5)    // Date
                    );
                }
            } catch (SQLException ex) {
                System.out.println("Select.unique(id: String) error.");
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        }
        return order;
    }

    public static Product uniqueProduct(String id) {
        // Initialize product object
        Product product = null;

        if (connection == null) {
            try {
                // Connect to the Database
                connect();

                // Create Query, Statement, and ResultSet
                String query = "SELECT * from Product WHERE Product_ID ='" + id +"';";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                // Create Product based on Unique ID
                while(rs.next()) {
                    product = new Product(
                            rs.getString(1),
                            rs.getDouble(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5)
                    );
                }
            } catch (SQLException ex) {
                System.out.println("Select.unique(table: String, id: String) error.");
            } finally {
                // Disconnect from the Database
                disconnect();
            }
        }
        return product;
    }

/*    public static ArrayList<Record> customQuery(String query) {
        ArrayList<Record> recList = new ArrayList<>();
        if (connection == null) {
            try {
                connect();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                if (query.contains("Product")) {
                    while (rs.next()) {
                        Product r = new Product(
                                rs.getString(1),
                                rs.getDouble(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getString(5)
                        );
                        assert false;
                        recList.add(r);
                    }
                } else if (query.contains("Customer")) {
                    while (rs.next()) {
                        Customer r = new Customer(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6)
                        );
                        assert false;
                        recList.add(r);
                    }
                } else if (query.contains("Order")) {
                    while (rs.next()) {
                        Order r = new Order(
                                rs.getString(1),
                                rs.getDouble(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getDate(5)
                        );
                        assert false;
                        recList.add(r);
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Select.customer(table: String, id: String) error.");
            }
            disconnect();
        }
        return recList;
    }*/
}

