package ProductOrdering.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import ProductOrdering.database.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class NewOrderController {

    @FXML private ComboBox productDropdown;
    @FXML private Button addToCart;
    @FXML private Button close;
    @FXML private Button gotoCustomerList;
    @FXML private TextField itemQuantity;
    @FXML private TableColumn itemNameCol;
    @FXML private TableColumn itemQuanCol;
    @FXML private TableColumn itemPriceCol;
    @FXML private TableColumn totalPriceCol;
    @FXML private TableView productTable;
    @FXML private Label orderTotal;
    private double total = 0;


    List items = new ArrayList();

    public void initialize(){
        List products = new ArrayList();
        products = Select.allProducts();

        ObservableList<Product> productsList = FXCollections.observableArrayList(products);
        productDropdown.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> listView) {

                final ListCell<Product> cell = new ListCell<Product>(){
                    @Override
                    protected void updateItem(Product p, boolean bln){
                        super.updateItem(p, bln);

                        if(p != null){
                            setText(p.getItemName());
                        }
                        else{
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        productDropdown.setItems(productsList);
        productDropdown.setConverter(new ProductConverter());
        productDropdown.setPromptText("Select Product");
        productDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product p = (Product) productDropdown.getValue();
            }
        });


        addToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product p = (Product) productDropdown.getValue();
                if(itemQuantity.getText().equals("") && p == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select an item, and enter an item quantity");
                    itemQuantity.setStyle("-fx-control-inner-background: red;");
                    alert.showAndWait();
                }
                else if(itemQuantity.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an item quantity");
                    itemQuantity.setStyle("-fx-control-inner-background: red;");
                    alert.showAndWait();
                }
                else if(Integer.parseInt(itemQuantity.getText()) > p.getStock()){
                    System.out.println("Not enough stock");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Not enough stock");
                    itemQuantity.clear();
                    alert.showAndWait();
                }
                else{
                    OrderDetails orderDetails = new OrderDetails(p.getItemName(), Integer.parseInt(itemQuantity.getText()), p.getItemPrice(), p.getProductID());
                    updateTable(orderDetails);
                    itemQuantity.clear();
                    productDropdown.getSelectionModel().clearSelection();
                }

            }
        });

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
                    close.getScene().setRoot(root);
                }
                catch(Exception e){
                    System.out.println("error loading home.fxml");
                }
            }
        });

        itemQuantity.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                itemQuantity.setStyle("-fx-control-inner-background: white;");
            }
        });

        gotoCustomerList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader((getClass().getResource("../fxml/customerList.fxml")));
                    Parent root = loader.load();
                    CustomerListController controller = loader.getController();
                    controller.receiveData(items, total);
                    close.getScene().setRoot(root);
                }
                catch(IOException e){
                    System.err.println(e);
                }
            }
        });
    }

    public void updateTable(OrderDetails o){
        items.add(o);
        ObservableList<Product> itemTableList = FXCollections.observableArrayList(items);
        itemNameCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, String>("itemName"));
        itemQuanCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, Integer>("itemQuantity"));
        itemPriceCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, Double>("itemPrice"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, Double>("total"));
        productTable.setItems(itemTableList);
        total += o.getTotal();
        orderTotal.setText("$" + total);
    }
}


