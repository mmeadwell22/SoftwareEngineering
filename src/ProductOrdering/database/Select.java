package ProductOrdering.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Select extends DatabaseUtil {

    public static ArrayList<Record> all(String table) {
        ArrayList<Record> recList = null;

        try {
            String query = "SELECT * from " + table;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (table.equals("Product")) {
                while (rs.next()) {
                    Record r = new Product(
                            rs.getString(1),
                            rs.getDouble(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5)
                    );
                    assert false;
                    recList.add(r);
                }
            } else if (table.equals("Customer")) {
                while (rs.next()) {
                    Record r = new Customer(
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
            } else if (table.equals("Order")) {
                while (rs.next()) {
                    Record r = new Order(
                            rs.getString(1),
                            rs.getDouble(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                    );
                    assert false;
                    recList.add(r);
                }
            }

        } catch (SQLException ex) {
            return null;
        }

        return recList;
    }

    public static Record unique(String table, String id) {
        Record record = null;
        try {
            String query = "SELECT * from " + table;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (table.equals("Product")) {
                record = new Product(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );

            } else if (table.equals("Customer")) {

                record = new Customer(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6)
                    );


            } else if (table.equals("Order")) {

                    record = new Order(
                            rs.getString(1),
                            rs.getDouble(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                    );

                }


        } catch (SQLException ex) {
            return null;
        }

        return record;
    }

    public static ArrayList<Record> customQuery(String query) {
        ArrayList<Record> recList = null;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (query.contains("Product")) {
                while (rs.next()) {
                    Record r = new Product(
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
                    Record r = new Customer(
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
                    Record r = new Order(
                            rs.getString(1),
                            rs.getDouble(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                    );
                    assert false;
                    recList.add(r);
                }
            }

        } catch (SQLException ex) {
            return null;
        }

        return recList;
    }
}

