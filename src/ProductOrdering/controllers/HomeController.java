package ProductOrdering.controllers;

import ProductOrdering.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.WindowEvent;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeController {

    //grabs references to all the fxml objects by the fx:id in the corresponding fxml file.
    @FXML private Button refresh;
    @FXML private TableView pendingOrderTable;
    @FXML private TableColumn nameCol;
    @FXML private TableColumn orderidCol;
    @FXML private TableColumn emailCol;
    @FXML private TableColumn addressCol;
    @FXML private TableColumn phoneCol;
    @FXML private TableColumn dateCol;

    @FXML private TableView incompleteOrderTable;
    @FXML private ScrollPane pendingOrderScroll;
    @FXML private ScrollPane incompleteOrderScroll;
    @FXML private MenuBar menuBar;

    //this function is ran whenever a new controller is initialized
    public void initialize(){
        //sets the preferred width of the two tables
        pendingOrderTable.prefWidthProperty().bind(pendingOrderScroll.widthProperty());
        incompleteOrderTable.prefWidthProperty().bind(incompleteOrderScroll.widthProperty());

        //creates a list to hold all orders for the tableview
        List tableOrders = new ArrayList();
        tableOrders = GetDataFromDatabase(tableOrders);

        //this creates the observable list that the tables references.
        ObservableList<Order> tableOrderList = FXCollections.observableArrayList(tableOrders);
        //sets the minimum width of certain table cells
        addressCol.setMinWidth(175);
        dateCol.setMinWidth(100);
        nameCol.setMinWidth(100);
        //the lines of code assign values to specific columns in the table view.
        nameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Order, String>("deliveryAddress"));
        orderidCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Order, Date>("orderDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Order, String>("customerPhone"));
        pendingOrderTable.setItems(tableOrderList);



        //creates a selection model object to allow for interacting with the table.
        TableView.TableViewSelectionModel<Order> selectionModel = pendingOrderTable.getSelectionModel();

        //creates a new event handler that is called whenever a table row is clicked on
        pendingOrderTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (mouseEvent.isPrimaryButtonDown()) {
                        System.out.println(selectionModel.getSelectedItem().getCustomerName());
                        viewOrder(mouseEvent, selectionModel.getSelectedItem());
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                initialize();
            }
        });
    }

    public List GetDataFromDatabase(List list){
        //retrieves all order in the database
        List orders = Select.allOrders();
        //this for loop goes through all orders in the database and assigns the correct customer to the correct order
        for(int i = 0; i < orders.size(); i++){
            Order single = (Order) orders.get(i);
            Customer customer = Select.uniqueCustomer(single.getCustomerID());
            single.setCustomer(customer);
            list.add(single);
        }
        return list;
    }

    //function calls a new page to be loaded.
    public void viewOrder(MouseEvent event, Order order) throws Exception{
        //Stage stage = (Stage) order.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("../fxml/order.fxml"));
        //stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));

        Stage modal = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/order.fxml"));
        Parent root = loader.load();
        OrderController orderController = loader.getController();
        orderController.GetData(order);
        modal.setScene(new Scene(root));
        modal.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                initialize();
            }
        });
        modal.setTitle("Order Info");
        modal.initModality(Modality.WINDOW_MODAL);
        modal.initOwner(((Node)event.getSource()).getScene().getWindow());
        modal.show();
    }
}
