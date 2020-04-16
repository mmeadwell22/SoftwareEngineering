package ProductOrdering.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import ProductOrdering.database.*;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;
import java.util.ArrayList;


public class NewOrderController {

    @FXML private ComboBox productDropdown;
    @FXML private VBox vbox;

    public void initialize(){
        List products;
        products = Select.allProducts();

        ObservableList<Product> productsList = FXCollections.observableArrayList(products);
        productDropdown.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> listView) {

                final ListCell<Product> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Product p, boolean bln) {
                        super.updateItem(p, bln);

                        if (p != null) {
                            setText(p.getItemName());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        productDropdown.setItems(productsList);
        productDropdown.setConverter(new ProductConverter());
        productDropdown.setOnAction(actionEvent -> {
            Product p = (Product) productDropdown.getValue();
            System.out.println(p.getItemPrice());
        });
    }
}
