package ProductOrdering.database;

import java.sql.*;

public abstract class Update extends DatabaseUtil {

    public void customer(String CID, String name,
                               String contact, String address,
                               String payment, String company)
            throws SQLException, ClassNotFoundException {
        customerName(name, CID);
        customerContact(contact, CID);
        customerAddress(address, CID);
        customerPayment(payment, CID);
        customerCompany(company, CID);
    }

    public void customerName(String name, String CID) {
        if (connection == null) {
            try {
                connect();

                String SQL = "Update dbo.Customer Set CName =" + name + "where CID =" + CID;
                Statement stm = connection.createStatement();
                ResultSet result = stm.executeQuery(SQL);
            } catch (SQLException e) {
                System.out.println("SQL exception " + e.getMessage());
            }
            disconnect();
        }
    }

    public void customerContact(String contact, String CID) {
        if (connection == null) {
            try {
                connect();

                String SQL = "Update dbo.Customer Set contact = " + contact + "where CID =" + CID;
                Statement stm = connection.createStatement();
                ResultSet result = stm.executeQuery(SQL);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("SQL exception " + e.getMessage());
            }
            disconnect();
        }
    }

    public void customerAddress(String address, String CID) {
        try {
            connect();

            String SQL = "Update dbo.Customer Set [Address] =" + address + "where CID =" + CID;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
            disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

    public void customerPayment(String payInfo, String CID) {
        if (connection == null) {
            try {
                connect();

                String SQL = "Update dbo.Customer Set Payment_info =" + payInfo + "where CID =" + CID;
                Statement stm = connection.createStatement();
                ResultSet result = stm.executeQuery(SQL);
                disconnect();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("SQL exception " + e.getMessage());
            }
        }
    }

    public void customerCompany(String company, String CID) {
        if (connection == null) {
            try {
                connect();

                String SQL = "Update dbo.Customer Set company =" + company + "where CID =" + CID;
                Statement stm = connection.createStatement();
                ResultSet result = stm.executeQuery(SQL);

                disconnect();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("SQL exception " + e.getMessage());
            }
        }
    }

    //method for updating the Order_info table
    public void order(String newAddress, String Order_ID) {
        if (connection == null) {
            try {
                connect();

                String SQL = "Update dbo.Order_info Set Delivery_address =" + newAddress +
                        "where Order_ID =" + Order_ID;
                Statement stm = connection.createStatement();
                ResultSet result = stm.executeQuery(SQL);

                disconnect();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("SQL exception " + e.getMessage());
            }
        }
    }

    public void productName(String Pname, String PID) {
        if (connection == null) {
            try {
                connect();

                String SQL = "Update dbo.Product Set Product_Name =" + Pname +
                        "where PID =" + PID;
                Statement stm = connection.createStatement();
                ResultSet result = stm.executeQuery(SQL);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("SQL exception " + e.getMessage());
            }
        }
    }
    //end of Quang's code
}
