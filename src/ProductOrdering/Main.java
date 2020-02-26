package ProductOrdering;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("testing actions");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


/*    class OKHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
            System.out.println("Ok button clicked");
        }
    }

    class CancelHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
            System.out.println("Cancel button clicked");
        }
    }*/


    public static void main(String[] args) {
        Application.launch(args);
    }
}
