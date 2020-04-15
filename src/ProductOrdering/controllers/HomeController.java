package ProductOrdering.controllers;

import ProductOrdering.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeController {

    //grabs references to all the fxml objects by the fx:id in the corresponding fxml file.
    @FXML private Button refresh;
    @FXML private Button newProduct;
    @FXML private Button newOrder;
    @FXML private TableView<Order> pendingOrderTable;
    @FXML private TableColumn<Order, String> nameCol;
    @FXML private TableColumn<Order, String> orderidCol;
    @FXML private TableColumn emailCol;
    @FXML private TableColumn<Order, String> addressCol;
    @FXML private TableColumn<Order, String> phoneCol;
    @FXML private TableColumn<Order, Date> dateCol;
    @FXML private TableView incompleteOrderTable;
    @FXML private ScrollPane pendingOrderScroll;
    @FXML private ScrollPane incompleteOrderScroll;

    //this function is ran whenever a new controller is initialized
    public void initialize(){
        //sets the preferred width of the two tables
        pendingOrderTable.prefWidthProperty().bind(pendingOrderScroll.widthProperty());
        incompleteOrderTable.prefWidthProperty().bind(incompleteOrderScroll.widthProperty());

        //creates a list to hold all orders for the tableview
        List<Order> tableOrders = new ArrayList<>();
        tableOrders = GetDataFromDatabase(tableOrders);

        //this creates the observable list that the tables references.
        ObservableList<Order> tableOrderList = FXCollections.observableArrayList(tableOrders);

        //sets the minimum width of certain table cells
        addressCol.setMinWidth(175);
        dateCol.setMinWidth(100);
        nameCol.setMinWidth(100);

        //the lines of code assign values to specific columns in the table view.
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        orderidCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        pendingOrderTable.setItems(tableOrderList);

        //creates a selection model object to allow for interacting with the table.
        TableView.TableViewSelectionModel<Order> selectionModel = pendingOrderTable.getSelectionModel();

        //creates a new event handler that is called whenever a table row is clicked on
        pendingOrderTable.setOnMousePressed(mouseEvent -> {
            try {
                if (mouseEvent.isPrimaryButtonDown()) {
                    System.out.println(selectionModel.getSelectedItem().getCustomerName());
                    viewOrder(mouseEvent, selectionModel.getSelectedItem());
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });

        //when refresh button is click data is retrieved from database
        refresh.setOnAction(actionEvent -> initialize());

        //when new order button is click go to new scene
        newProduct.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../fxml/newProduct.fxml"));
                    newProduct.getScene().setRoot(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        newOrder.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../fxml/newOrder.fxml"));
                    newOrder.getScene().setRoot(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //function that is responsible for retrieveing data from database
    public List<Order> GetDataFromDatabase(List<Order> list){
        //retrieves all order in the database
        List<Order> orders = Select.allOrders();

        //this for loop goes through all orders in the database and assigns the correct customer to the correct order
        for(int i = 0; i < orders.size(); i++){
            Order single = orders.get(i);
            Customer customer = Select.uniqueCustomer(single.getCustomerID());
            single.setCustomer(customer);
            list.add(single);
        }
        return list;
    }

    //function calls a new modal to be loaded.
    public void viewOrder(MouseEvent event, Order order) throws Exception{
        Stage modal = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/order.fxml"));
        Parent root = loader.load();
        OrderController orderController = loader.getController();
        orderController.GetData(order);
        modal.setScene(new Scene(root));
        modal.setOnHidden(windowEvent -> initialize());
        modal.setTitle("Order Info");
        modal.initModality(Modality.WINDOW_MODAL);
        modal.initOwner(((Node)event.getSource()).getScene().getWindow());
        modal.show();
    }
}
