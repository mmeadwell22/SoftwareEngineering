package ProductOrdering.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.awt.*;

public class LoginController {

    private String UserName = "dev";
    private String Password = "asdf1234";

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button test;
    @FXML
    private Text errorText;

    public void initialize(){
        test.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    try {
                        login();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void login() throws Exception{
        if(UserName.equals(username.getText()) && Password.equals(password.getText())){
            System.out.println("login successful");
            Stage stage = (Stage) username.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/home.fxml"));
            stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
        }
        else{
            errorText.setText("Incorrect username or password");
        }
    }

}
