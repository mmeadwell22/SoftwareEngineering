package ProductOrdering.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class InsertValue extends DatabaseUtil {
    public static void insert(String table){
        try {
            if(connection == null){
                throw new SQLException();
            }

            String insertQ = "INSERT INTO " + table +  " VALUES " + "()";;

            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertQ, Statement.RETURN_GENERATED_KEYS);
            prepsInsertProduct.execute();

            // Retrieve the generated key from the insert.
            ResultSet resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                // System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
