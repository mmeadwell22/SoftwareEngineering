package ProductOrdering.database;
import java.sql.*;


public abstract class DatabaseUtil implements Connection{
    // Database Connection String
    protected static String connectionUrl =
            "jdbc:sqlserver://yourserver.database.windows.net:1433;"
                    + "database=OrderingSystem;"
                    + "user=User0@NOELS-DESKTOP;"
                    + "password=password;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";

    // Connect object for database connection
    protected static Connection connection = null;

    // Connect to the database
    public static void connect() {
        try {
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection to the Database Succeeded.");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
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
