package ProductOrdering.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {


    @FXML
    private Button order;


    public void initialize(){
        order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    order();
                    System.out.println("Going to order");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void order() throws Exception{
        System.out.println("in order");
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/order.fxml"));
        order.getScene().setRoot(root);
    }



}
