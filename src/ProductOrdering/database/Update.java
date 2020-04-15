package ProductOrdering.database;

import java.sql.*;

public abstract class Update extends DatabaseUtil {
    private static String updateCustomerSet = "UPDATE Customer SET ";
    private static String whereCustomerID = "' WHERE Customer_ID = '";

    private static String updateOrderSet = "UPDATE [Order] SET ";
    private static String whereOrderID = "' WHERE Order_ID ='";

    private static String updateProductSet = "UPDATE Product SET ";
    private static String whereProductID = "' WHERE Product_ID ='";

    public void updateCustomer( String CID, String name, String contact, String address, String payment, String company) throws SQLException, ClassNotFoundException {
        customerName(name, CID);
        customerContact(contact, CID);
        customerAddress(address, CID);
        customerPayment(payment, CID);
        customerCompany(company, CID);
    }

    public static void  customerName(String name, String CID) {
        String updateQ = updateCustomerSet + "Customer_Name ='" + name + whereCustomerID + CID + "';";

        // Report error if SQLStatement failed to execute
        if (execute(updateQ) == null){
            System.out.println("Failed to update customer name.");
        }
    }

    public void customerContact (String contact, String CID) {
        String updateQ = updateCustomerSet + "Contact ='" + contact + whereCustomerID + CID + "';";

        // Report error if SQLStatement failed to execute
        if (execute(updateQ) == null){
            System.out.println("Failed to update customer contact.");
        }
    }

    public void customerAddress (String address, String CID)  {
        String updateQ = updateCustomerSet + "[Address] = '" + address + whereCustomerID + CID + "';";

        // Report error if SQLStatement failed to execute
        if (execute(updateQ) == null){
            System.out.println("Failed to update customer address.");
        }
    }

    public void customerPayment (String payInfo, String CID) {
        String updateQ = updateCustomerSet + "Payment_Info ='" + payInfo + whereCustomerID + CID + "';";

        // Report error if SQLStatement failed to execute
        if (execute(updateQ) == null){
            System.out.println("Failed to update customer address.");
        }
    }

    public void customerCompany (String company, String CID) {
        String updateQ = updateCustomerSet + " Company_Name ='" + company + whereCustomerID + CID + "';";

        // Report error if SQLStatement failed to execute
        if (execute(updateQ) == null){
            System.out.println("Failed to update customer company.");
        }
    }

    //method for updating the Order_info table
    public void updateOrder (String newAddress, String Order_ID) {
        String updateQ = updateOrderSet + " Delivery_Address ='" + newAddress  + whereOrderID + Order_ID + "';";

        // Report error if SQLStatement failed to execute
        if (execute(updateQ) == null){
            System.out.println("Failed to update order address.");
        }
    }

    public void updateProductName (String Pname, String PID) {
        String updateQ = updateProductSet + "Product_Name ='" + Pname + whereProductID + PID + "';";

        // Report error if SQLStatement failed to execute
        if (execute(updateQ) == null){
            System.out.println("Failed to update order address.");
        }
    }
}