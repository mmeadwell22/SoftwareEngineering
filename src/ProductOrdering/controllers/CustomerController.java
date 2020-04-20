package ProductOrdering.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;

public class CustomerController {

    @FXML
    private Button gotoInfo;

    public void initialize(){

        gotoInfo.setOnAction(event -> {
            try {
                gotoInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void gotoInfo() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("src/ProductOrdering/fxml/customerInfo.fxml"));
        gotoInfo.getScene().setRoot(root);
    }
}
