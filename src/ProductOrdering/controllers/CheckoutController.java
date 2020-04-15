package ProductOrdering.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckoutController {

    @FXML
    private Button home;

    public void initialize(){
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gotoInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void gotoInfo() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        home.getScene().setRoot(root);
    }
}
