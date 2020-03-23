package ProductOrdering.database;

import java.sql.*;

public abstract class Update extends DatabaseUtil {

    public void updateCustomer(String name, String CID,  String contact, String payment, String company, String oldCID) throws SQLException, ClassNotFoundException {

        try {
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Ordering System;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQLdelete = ("Delete Customer Where CID =" + oldCID);
            String SQLinsert = String.format("INSERT INTO dbo.Customer Values (%s, %s, %s, %s, %s)", name, CID,contact, payment, company);
            Statement stm = connection.createStatement();

            ResultSet result = stm.executeQuery(SQLinsert);


        }
        catch(ClassNotFoundException e ) {System.out.println(e.getMessage());
         }
        catch(SQLException e){
                System.out.println("SQL exception " + e.getMessage());
        }
    }
    public void  updateCustomerName(String name, String oldName) {
        try {
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Ordering System;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQL = "Update dbo.Customer Set CName =" + name +"where name =" +oldName;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
        }
        catch(ClassNotFoundException e) {System.out.println(e.getMessage());
        }
        catch(SQLException e){
            System.out.println("SQL exception " + e.getMessage());
        }
   // closeSQL(connection, stm, result);
    }

    public void updateCustomerContact (String contact, String oldContact) {
        try {
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Ordering System;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQL = "Update dbo.Customer Set contact =" +contact + "where contact =" +oldContact;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

   public void updateCustomerAddress (String address, String oldAddress) {
        try {
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Ordering System;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQL = "Update dbo.Customer Set Address =" + address + "where address =" +oldAddress;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

    public void updateCustomerPayment (String payInfo, String oldPayInfo) {
        try {
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Ordering System;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQL = "Update dbo.Customer Set Payment_info =" +payInfo + "where payment_info =" +oldPayInfo;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);

        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
            System.out.println("SQL exception " + e.getMessage());
        }
    }

    public void updateCustomerCompany (String company, String oldCompany) {
        try {
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=Ordering System;integratedSecurity=true";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQL = "Update dbo.Customer Set company =" + company+ "where payment_info =" +oldCompany;
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);
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
