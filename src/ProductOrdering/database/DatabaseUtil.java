package ProductOrdering.database;
import java.sql.*;


public abstract class DatabaseUtil implements Connection{
    // Database Connection String
    protected static String connectionUrl =
            "jdbc:sqlserver://localhost:1433;databaseName=OrderSystem;integratedSecurity=true;";

    // Connect object for database connection
    protected static Connection connection = null;

    // Connect to the database
    public static void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection to the Database Succeeded.");
        }
        // Handle any errors that may have occurred.
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection to the Database Failed.");
        }
    }

    // Disconnect from the database
    public static void disconnect()  {
        try{
            connection.close();
            System.out.println("Successfully closed the connection to the database.");
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Failed to close the connection to the database.");
        }
    }
}
