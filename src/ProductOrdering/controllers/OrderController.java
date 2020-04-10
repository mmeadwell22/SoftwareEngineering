package ProductOrdering.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderController {


    @FXML
    private Button close;
    @FXML
    private Button gotoCustomerList;


    public void initialize(){
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        gotoCustomerList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gotoCustomerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void close() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        close.getScene().setRoot(root);
    }
    public void gotoCustomerList() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/customerList.fxml"));
        close.getScene().setRoot(root);
    }


}
