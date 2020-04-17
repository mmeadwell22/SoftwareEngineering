package ProductOrdering.database;
import java.sql.*;


public abstract class DatabaseUtil implements Connection {

    // Connect object for database connection
    private static Connection connection = null;

    // Connect to the database
    public static void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Database Connection String
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=OrderSystem;integratedSecurity=true;";
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
    public static void disconnect() throws SQLException {
            connection.close();
            connection = null;
            System.out.println("Successfully closed the connection to the database.");
    }

    public static ResultSet execute(String query) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        return ps.executeQuery();
    }

    public static void update(String query) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(query);
        int row = ps.executeUpdate();
    }
}
