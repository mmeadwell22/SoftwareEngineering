package ProductOrdering.database;

import java.sql.*;

public abstract class Update extends DatabaseUtil {

    public void updateCustomer( String CID, String name, String contact, String address, String payment, String company) throws SQLException, ClassNotFoundException {
        updateCustomerName(name, CID);
        updateCustomerContact(contact, CID);
        updateCustomerAddress(address, CID);
        updateCustomerPayment(payment, CID);
        updateCustomerCompany(company, CID);
    }
    public void  updateCustomerName(String name, String CID) {
        if (connection == null) {
        try {
           connect();

            String SQL = "Update dbo.Customer Set CName =" + name +"where CID =" +CID;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
            
            disconnect();
        }
        catch(ClassNotFoundException e) {System.out.println(e.getMessage());
        }
        catch(SQLException e){
            System.out.println("SQL exception " + e.getMessage());
        }
            
        }
    }

    public void updateCustomerContact (String contact, String CID) {
        try {
            connect();

            String SQL = "Update dbo.Customer Set contact =" +contact + "where CID =" +CID;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
            disconnect();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

   public void updateCustomerAddress (String address, String CID) {
        try {
           connect()

            String SQL = "Update dbo.Customer Set [Address] =" + address + "where CID =" + CID;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
            disconnect();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

    public void updateCustomerPayment (String payInfo, String CID) {
        try {
            connect();

            String SQL = "Update dbo.Customer Set Payment_info =" +payInfo + "where CID =" + CID;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
            disconnect();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

    public void updateCustomerCompany (String company, String CID) {
        try {
           connect();

            String SQL = "Update dbo.Customer Set company =" + company+ "where CID =" +CID;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
            
            disconnect();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

    //method for updating the Order_info table
    public void updateOrder (String newAddress, String oldAddress) {
        try {
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Ordering System;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQL = "Update dbo.Order_info Set Delivery_address =" + newAddress  +
                        "where Delivery_address =" +oldAddress;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }
    //end of Quang's code
}
