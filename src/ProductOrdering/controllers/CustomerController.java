package ProductOrdering.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerController {

    @FXML
    private Button gotoInfo;

    public void initialize(){
        gotoInfo.setOnAction(new EventHandler<ActionEvent>() {
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
        Stage stage = (Stage) gotoInfo.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/customerInfo.fxml"));
        stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
    }
}
