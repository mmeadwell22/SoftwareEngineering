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
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {

    //grabs references to all the fxml objects by the fx:id in the corresponding fxml file.
    @FXML private MenuBar menuBar;
    @FXML private TableView pendingOrderTable;
    @FXML private TableColumn nameCol;
    @FXML private TableColumn orderidCol;
    @FXML private TableColumn addressCol;
    @FXML private TableColumn phoneCol;
    @FXML private TableColumn dateCol;
    @FXML private TableView incompleteOrderTable;
    @FXML private ScrollPane pendingOrderScroll;
    @FXML private ScrollPane incompleteOrderScroll;
    @FXML private DatePicker datePicker;

    //this function is ran whenever a new controller is initialized
    public void initialize(){
        //sets the preferred width of the two tables
        pendingOrderTable.prefWidthProperty().bind(pendingOrderScroll.widthProperty());
        incompleteOrderTable.prefWidthProperty().bind(incompleteOrderScroll.widthProperty());

        //sets the minimum width of certain table cells
        addressCol.setMinWidth(175);
        dateCol.setMinWidth(100);
        nameCol.setMinWidth(100);
        updateTable();

        Menu menu1 = new Menu("Menu");
        MenuItem refresh = new MenuItem("Refresh");
        MenuItem newOrder = new MenuItem("New Order");
        MenuItem newProduct = new MenuItem("New Product");
        menu1.getItems().add(refresh);
        menu1.getItems().add(newOrder);
        menu1.getItems().add(newProduct);
        menuBar.getMenus().add(menu1);

        refresh.setOnAction(actionEvent -> updateTable());

        newOrder.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("src/ProductOrdering/fxml/newOrder.fxml"));
                menuBar.getScene().setRoot(root);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        newProduct.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../fxml.newProduct.fxml"));
                menuBar.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //creates a selection model object to allow for interacting with the table.
        TableView.TableViewSelectionModel<Order> selectionModel = pendingOrderTable.getSelectionModel();

        //creates a new event handler that is called whenever a table row is clicked on
        pendingOrderTable.setOnMousePressed(mouseEvent -> {
            try {
                if (mouseEvent.isPrimaryButtonDown()) {
                    viewOrder(mouseEvent, selectionModel.getSelectedItem());
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });

        //potential to add filtering based on date.
        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

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

    public void updateTable(){
        List<Order> tableOrders = new ArrayList<>();
        tableOrders = GetDataFromDatabase(tableOrders);

        ObservableList<Order> tableOrderList = FXCollections.observableArrayList(tableOrders);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        orderidCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        pendingOrderTable.setItems(tableOrderList);

    }

    //function calls a new modal to be loaded.
    public void viewOrder(MouseEvent event, Order order) throws Exception{
        Stage modal = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/ProductOrdering/fxml/order.fxml"));
        Parent root = loader.load();
        OrderController orderController = loader.getController();
        orderController.GetData(order);
        modal.setScene(new Scene(root));
        modal.setOnHidden(windowEvent -> updateTable());
        modal.setTitle("Order Info");
        modal.initModality(Modality.WINDOW_MODAL);
        modal.initOwner(((Node)event.getSource()).getScene().getWindow());
        modal.show();
    }
}
