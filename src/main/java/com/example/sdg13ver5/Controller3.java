package com.example.sdg13ver5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller3 {
    @FXML
    Pane display2;

    private Stage stage;
    private Scene scene;


    //Button To switch back to Login Page
    public void admintomain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 360);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    //Button To Switch Between Admin Pages
    @FXML
    public void adminone() throws IOException {
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage1.fxml"));
        display2.getChildren().setAll(viewpage);
    }
    @FXML
    public void admintwo() throws IOException {
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage2.fxml"));
        display2.getChildren().setAll(viewpage);
    }
    @FXML
    public void adminthree() throws IOException {
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage3.fxml"));
        display2.getChildren().setAll(viewpage);
    }
    @FXML
    public void adminfour() throws IOException {
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage4.fxml"));
        display2.getChildren().setAll(viewpage);
    }
    @FXML
    public void adminfive() throws IOException {
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage5.fxml"));
        display2.getChildren().setAll(viewpage);
    }
    @FXML
    public void adminsix() throws IOException {
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage6.fxml"));
        display2.getChildren().setAll(viewpage);
    }


}

