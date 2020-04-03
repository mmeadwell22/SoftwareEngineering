package ProductOrdering.database;

import java.util.ArrayList;

public class DatabaseTEST {
    public static void main(String[] args) {
        Insert.customer("Noel Mulcahy", "123456789", "18130001999",
                "1234 Euclid, Cleveland OH", "4060111344453211", "NXSYS");
        ArrayList<Customer> ar = Select.allCustomers();
        System.out.println(ar.get(0));
    }
}
