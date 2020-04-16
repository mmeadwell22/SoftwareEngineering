package ProductOrdering.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/customer.fxml"));
        gotoShop.getScene().setRoot(root);
    }

}
