package ProductOrdering.controllers;

import ProductOrdering.database.Product;
import javafx.util.StringConverter;

public class ProductConverter extends StringConverter<Product> {

    @Override
    public String toString(Product p){
        return p == null ? null : p.getItemName();
    }

    @Override
    public Product fromString(String s){
        Product p = null;

        if(s == null){
            return p;
        }
        return p;
    }
}
