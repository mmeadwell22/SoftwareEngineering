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


    }

    public void close() throws Exception{
        Stage stage = (Stage) close.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        close.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
    }



}
