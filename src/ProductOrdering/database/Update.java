package ProductOrdering.database;

import java.sql.*;

public abstract class Update extends DatabaseUtil {
    private static final String updateCustomerSet = "UPDATE Customer SET ";
    private static final String whereCustomerID = "' WHERE Customer_ID = '";

    private static final String updateOrderSet = "UPDATE [Order] SET ";
    private static final String whereOrderID = "' WHERE Order_ID ='";

    private static final String updateProductSet = "UPDATE Product SET ";
    private static final String whereProductID = "' WHERE Product_ID ='";

    public void updateCustomer( String CID, String name, String contact, String address, String payment, String company) throws SQLException, ClassNotFoundException {
        customerName(name, CID);
        customerContact(contact, CID);
        customerAddress(address, CID);
        customerPayment(payment, CID);
        customerCompany(company, CID);
    }

    public static void  customerName(String name, String CID) {
        String updateQ = updateCustomerSet + "Customer_Name ='" + name + whereCustomerID + CID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void customerContact (String contact, String CID) {
        String updateQ = updateCustomerSet + "Contact ='" + contact + whereCustomerID + CID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void customerAddress (String address, String CID)  {
        String updateQ = updateCustomerSet + "[Address] = '" + address + whereCustomerID + CID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void customerPayment (String payInfo, String CID) {
        String updateQ = updateCustomerSet + "Payment_Info ='" + payInfo + whereCustomerID + CID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void customerCompany (String company, String CID) {
        String updateQ = updateCustomerSet + " Company_Name ='" + company + whereCustomerID + CID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    //method for updating the Order_info table
    public static void updateOrder (String newAddress, String Order_ID) {
        String updateQ = updateOrderSet + " Delivery_Address ='" + newAddress  + whereOrderID + Order_ID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void updateProductName (String Pname, String PID) {
        String updateQ = updateProductSet + "Product_Name ='" + Pname + whereProductID + PID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){ e.printStackTrace(); }
    }

    public static void updateProductStock(int newStock, String PID){
        PID = PID.trim();
        String updateQ = updateProductSet + "Stock = '" + newStock + whereProductID + PID + "';";
        try{
            connect();
            update(updateQ);
            disconnect();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}