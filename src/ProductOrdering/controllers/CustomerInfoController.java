package ProductOrdering.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;

public class CustomerInfoController {

    @FXML
    private Button gotoShop;

    public void initialize(){
        gotoShop.setOnAction(event -> {
            try {
                gotoShop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void gotoShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("src/ProductOrdering/fxml/customer.fxml"));
        gotoShop.getScene().setRoot(root);
    }

}
