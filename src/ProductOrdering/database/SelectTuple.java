package ProductOrdering.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SelectTuple extends DatabaseUtil {

    private static Statement statement;

    public static Record[] selectAll(String table) {

    }

    public static Record selectUnique(){

    }

    public static Record[] customQuery(){

    }

}

/* selectAll for each table
    selectUnique for each table returns record
    customQuery
    add for each table
    remove for each table
    create a custom record class

            try {
            if (connection == null) {
                throw new SQLException();
            }

            Statement statement = connection.createStatement();

            // Create and execute a SELECT SQL statement.

            String selectSql = "SELECT TOP 10 Title, FirstName, LastName from SalesLT.Customer";
            ResultSet resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 */

