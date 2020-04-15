package ProductOrdering.controllers;

import ProductOrdering.database.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OrderController {

    @FXML private TextField nameText;
    @FXML private TextField orderidText;
    @FXML private TextField emailText;
    @FXML private TextField productText;
    @FXML private TextField addressText;

    @FXML private Button acceptOrder;

    Order order;

    public void GetData(Order o){
        order = o;
        nameText.setText(order.getCustomerName());
        orderidText.setText(order.getOrderID());
        addressText.setText(order.getDeliveryAddress());
    }


    public void initialize(){
        acceptOrder.setOnAction(actionEvent -> UpdateData());
    }

    public void UpdateData(){
        Update.customerName(nameText.getText(), order.getCustomerID());
        Stage stage = (Stage) nameText.getScene().getWindow();
        stage.close();
    }

/*    public void close() throws Exception{
        Stage stage = (Stage) close.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
    }*/

}
