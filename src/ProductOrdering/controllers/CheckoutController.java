package ProductOrdering.controllers;

import ProductOrdering.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;


public class CheckoutController {

    @FXML private Button home;
    @FXML private TextField orderIdTextField;
    @FXML private TextField customerId;
    @FXML private TextField deliveryAddress;
    @FXML private TextField payment;
    @FXML private TextField totalTextField;

    @FXML private TableColumn itemNameCol;
    @FXML private TableColumn itemQuanCol;
    @FXML private TableColumn itemPriceCol;
    @FXML private TableColumn totalPriceCol;
    @FXML private TableView productTable;

    List productList;
    Customer customer;
    double total;
    Order order;

    public void initialize(){
        home.setOnAction(event -> {
            try {
                goHome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void receiveData(List products, Customer c, double totalPrice){
        productList = products;
        customer = c;
        total = totalPrice;

        ObservableList<OrderDetails> productTableList = FXCollections.observableArrayList(productList);
        itemNameCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, String>("itemName"));
        itemQuanCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, Integer>("itemQuantity"));
        itemPriceCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, Double>("itemPrice"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, Double>("total"));
        productTable.setItems(productTableList);

        int newOrderID = Select.maxOrderId();
        long millis = System.currentTimeMillis();
        order = new Order(Integer.toString(newOrderID + 1), total, customer.getCustomerID(), customer.getAddress(), new Date(millis));
        order.setCustomer(customer);
        orderIdTextField.setText(order.getOrderID());
        customerId.setText(customer.getCustomerID());
        deliveryAddress.setText(order.getDeliveryAddress());
        payment.setText(customer.getPaymentInfo());
        totalTextField.setText(Double.toString(order.getTotal()));
    }

    public void goHome() throws Exception{
        Insert.order(order.getOrderID(), order.getTotal(), order.getCustomerID(), order.getDeliveryAddress(), (Date) order.getOrderDate());
        for(int i = 0; i < productList.size(); i++){
            OrderDetails pDetails = (OrderDetails) productList.get(i);
            Product p = Select.uniqueProduct(pDetails.getProductID());
            int temp = p.getStock() - pDetails.getItemQuantity();

            Insert.orderItem(pDetails.getProductID(), pDetails.getItemQuantity(), order.getOrderID());
            Update.updateProductStock(temp, pDetails.getProductID());
        }
        Alert warn = new Alert(Alert.AlertType.CONFIRMATION);
        warn.setTitle("Order Placed");
        warn.setHeaderText(null);
        warn.setContentText("Your Order has been placed");
        warn.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
        home.getScene().setRoot(root);
    }
}
