package ProductOrdering.controllers;

import ProductOrdering.database.Insert;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class NewProductController {

    @FXML private TextField productName;
    @FXML private TextField productPrice;
    @FXML private TextField productID;
    @FXML private TextField productStock;
    @FXML private TextField productAddress;
    @FXML private Button insert;

    public void initialize(){
        insert.setOnAction(e -> insertIntoDatabase());
    }

    public void insertIntoDatabase(){
        String name = productName.getText();
        double price = Double.parseDouble(productPrice.getText());
        String id = productID.getText();
        int stock = Integer.parseInt(productStock.getText());
        String address = productAddress.getText();

        Insert.product(name, price, id, stock, address);

    }
}
