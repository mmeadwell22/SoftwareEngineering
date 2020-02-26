package ProductOrdering;

import javafx.fxml.FXML;
import java.sql.*;

import java.sql.SQLException;

public class Controller {

    Connection connection = null;
    Statement stm = null;
    ResultSet result = null;
    @FXML
    protected void onClick(){
        try{
            Connection connection = null;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=OrderSystem;user=program;password=;";
            connection = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to database");

            String SQL = "SELECT * FROM dbo.Catalog";
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(SQL);

            while(result.next()){
                System.out.println(result.getString(1) + "  " + result.getString(2) + "  " + result.getString(3));
            }

        }catch(ClassNotFoundException e){
            System.out.println("Class not found exception " + e.getMessage());
        }
        catch(SQLException e){
            System.out.println("SQL exception " + e.getMessage());
        }
    }
}
