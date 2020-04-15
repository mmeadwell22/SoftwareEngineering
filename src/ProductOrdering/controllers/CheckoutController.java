package ProductOrdering.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;


public class CheckoutController {

    @FXML
    private Button home;

    public void initialize(){
        home.setOnAction(event -> {
            try {
                goHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void goHome() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        home.getScene().setRoot(root);
    }
}
