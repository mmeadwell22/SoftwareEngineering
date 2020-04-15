package ProductOrdering.controllers;

import ProductOrdering.database.Customer;
import ProductOrdering.database.Insert;
import ProductOrdering.database.Order;
import ProductOrdering.database.Select;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerListController {

    @FXML private Button gotoCheckout;
    @FXML private TextField Name;
    @FXML private TextField CustomerID;
    @FXML private TextField Phone;
    @FXML private TextField Address;
    @FXML private TextField Company;
    @FXML private TextField Payment;

    @FXML private TableView CustomerTable;
    @FXML private TableColumn nameCol;
    @FXML private TableColumn cidCol;
    @FXML private TableColumn phoneCol;
    @FXML private TableColumn addressCol;
    @FXML private TableColumn companyCol;
    @FXML private TableColumn paymentCol;
    public void initialize(){
        //CustomerTable.prefWidthProperty().bind(CustomerTable.widthProperty());

        List tableCustomer = new ArrayList(); //list to hold all customers info
        tableCustomer = GetDataFromCustomer(tableCustomer);

        //create an observable list for customer table
        ObservableList<Customer> tableCustomerList = FXCollections.observableArrayList(tableCustomer);;
        // Name.getText(); //setText() to set text to user input
        // assign values to each column by calling methods in the Customer object
        nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        cidCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerID"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        companyCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("companyName"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("paymentInfo"));
        CustomerTable.setItems(tableCustomerList);

        //creates a selection model object to allow for table interaction.
        TableView.TableViewSelectionModel<Customer> selectionModel = CustomerTable.getSelectionModel();

        // function to respond when a table row is clicked on
        CustomerTable.setOnMousePressed(new EventHandler <MouseEvent>() {
             @Override
             public void handle(MouseEvent mouseEvent) {
                 try {
                     if (mouseEvent.isPrimaryButtonDown()) {
                         System.out.println(selectionModel.getSelectedItem().getCustomerName()); //selectionModel from line  66

                         // when a row is clicked, fill out the following information in the boxes
                         Customer c = selectionModel.getSelectedItem();
                         Name.setText(c.getCustomerName());
                         CustomerID.setText(c.getCustomerID());
                         Phone.setText(c.getPhone());
                         Address.setText(c.getAddress());
                         Company.setText(c.getCompanyName()); //had to rename this method from the Customer.java
                         Payment.setText(c.getPaymentInfo());

                     }
                 }
                 catch(Exception e){
                     e.printStackTrace();
                 }
             }

        });
        gotoCheckout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { //if a new customer type in their info
                //create new fields here
                String newName = Name.getText();
                String newCID = CustomerID.getText();
                String newPhone = Phone.getText();
                String newAddress = Address.getText();
                String newComp = Company.getText();
                String newPayment = Payment.getText();
                Customer c2;
                c2 = Select.uniqueCustomer(CustomerID.getText());
                //check if c2 is null. No try-catch block because it gave error how SQL exception will not be thrown
                if (c2 == null) {
                   Insert.customer(newName, newCID, newPhone, newAddress, newComp, newPayment); //pass in all fields here
                }
                else
                {
                    Alert warn = new Alert(Alert.AlertType.INFORMATION);
                    warn.setTitle("warning");
                    warn.setHeaderText(null);
                    warn.setContentText("Customer already exist");
                    warn.showAndWait();

                    //clear out all textfields
                    Name.setText("");
                    CustomerID.setText("");
                    Phone.setText("");
                    Address.setText("");
                    Company.setText("");
                    Payment.setText("");
                }
                try {
                    gotoShop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // retrieving data from Customer table
    private List GetDataFromCustomer(List list) {
        list = Select.allCustomers();

        return list;
    }

    public void gotoShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/checkout.fxml"));
        gotoCheckout.getScene().setRoot(root);
    }

}
