package ProductOrdering.controllers;

import ProductOrdering.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;



import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeController {

    @FXML private Button order;
    @FXML private TableView pendingOrderTable;
    @FXML private TableColumn nameCol;
    //@FXML private TableColumn addressCol;
    @FXML private TableView incompleteOrderTable;
    @FXML private ScrollPane pendingOrderScroll;
    @FXML private ScrollPane incompleteOrderScroll;

    public void initialize(){
        pendingOrderTable.prefWidthProperty().bind(pendingOrderScroll.widthProperty());
        incompleteOrderTable.prefWidthProperty().bind(incompleteOrderScroll.widthProperty());

        //your program might not work if you have no data in the product table in your database. so either comment this out or
        //place data into product table.
        ArrayList<Product> data = new ArrayList<Product>();
        data = Select.allProducts();
        Product single = data.get(0);
        System.out.println(single.getItemName());


        order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    viewOrder();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void viewOrder() throws Exception{
        Stage stage = (Stage) order.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/order.fxml"));
        stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
    }
}
