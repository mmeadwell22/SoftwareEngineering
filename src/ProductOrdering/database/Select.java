package ProductOrdering.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Select extends DatabaseUtil {

    public static ArrayList<Customer> allCustomers() {
        ArrayList<Customer> recList = null;
        if (connection == null) {
            try {
                connect();
                String query = "SELECT * from CUSTOMER";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    assert false;
                    recList.add(new Customer(
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
                System.out.println("Select.all(table: String) error.");
            }
            disconnect();
        }
        return recList;
    }

    public static ArrayList<Order> allOrders() {
        ArrayList<Order> recList = null;
        if (connection == null) {
            try {
                connect();
                String query = "SELECT * from  ORDER";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    assert false;
                    recList.add(new Order(
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
            }
            disconnect();
        }
        return recList;
    }

    public static ArrayList<Product> allProducts() {
        ArrayList<Product> recList = null;
        if (connection == null) {
            try {
                connect();
                String query = "SELECT * from PRODUCT";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    assert false;
                    recList.add(new Product(
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
            }
            disconnect();
        }
        return recList;
    }

    public static Customer uniqueCustomer(String id) {
        if (connection == null) {
            try {
                connect();
                String query = "SELECT * from CUSTOMER";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                return new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            } catch (SQLException ex) {
                System.out.println("Select.unique(table: String, id: String) error.");
            }
            disconnect();
        }
        return null;
    }

    public static Order uniqueOrder(String id) {
        if (connection == null) {
            try {
                connect();
                String query = "SELECT * from ORDER";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                return new Order(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5)
                );
            } catch (SQLException ex) {
                System.out.println("Select.unique(table: String, id: String) error.");
            }
            disconnect();
        }
        return null;
    }

    public static Product uniqueProduct(String id) {
        if (connection == null) {
            try {
                connect();
                String query = "SELECT * from PRODUCT";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                return new Product(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
            } catch (SQLException ex) {
                System.out.println("Select.unique(table: String, id: String) error.");
            }
            disconnect();
        }
        return null;
    }

    public static ArrayList<Record> customQuery(String query) {
        ArrayList<Record> recList = null;
        if (connection == null) {
            try {
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
    }
}

