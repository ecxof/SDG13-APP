package com.example.sdg13ver5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Controller {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label invalid_user_pass;

    //Login
    @FXML
    public void login(ActionEvent event) throws IOException {

        String Varusername = username.getText();
        String Varpass = password.getText();
        if (Varusername.equals("123") && Varpass.equals("123")) {

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } else {
            username.clear();
            password.clear();
            invalid_user_pass.setText("Invalid Username Or Password");
        }
    }

    //Main page to exit
    public void exit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 360);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    //Main page to feedback
    public void tofeedback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("feedbackpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 540);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    TextField feedsugg;
    @FXML
    TextField feedname;
    @FXML
    Label feed_label;
    //Feedback page to Thank you page
    public void topostfeedback(ActionEvent event) throws IOException {

        String varfeedsugg = feedsugg.getText();
        String varfeedname = feedname.getText();
        if (varfeedsugg.isEmpty() || varfeedname.isEmpty()) {
            feed_label.setText("Please Give a Suggestion");
        } else {

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("postfeedback.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 600, 500);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }
    }
    //feedback page to main page
    public void feedtomain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }




    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button button3;
    @FXML
    Button button4;
    @FXML
    Button button5;
    @FXML
    Button button6;





    //display Pages
    @FXML
    Pane display1;

    @FXML
    public void pageone() throws IOException {
        Pane viewpage1 = FXMLLoader.load(getClass().getResource("page1.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_One_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        viewpage1.getChildren().add(label);
        display1.getChildren().setAll(viewpage1);
    }
    @FXML
    public void pagetwo() throws IOException {
        Pane viewpage2 = FXMLLoader.load(getClass().getResource("page2.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Two_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        viewpage2.getChildren().add(label);
        display1.getChildren().setAll(viewpage2);
    }

    @FXML
    public void pagethree() throws IOException {
        Pane viewpage3 = FXMLLoader.load(getClass().getResource("page3.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Three_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        viewpage3.getChildren().add(label);
        display1.getChildren().setAll(viewpage3);
    }

    @FXML
    public void pagefour() throws IOException {
        Pane viewpage4 = FXMLLoader.load(getClass().getResource("page4.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Four_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        viewpage4.getChildren().add(label);
        display1.getChildren().setAll(viewpage4);
    }

    @FXML
    public void pagefive() throws IOException {
        Pane viewpage5 = FXMLLoader.load(getClass().getResource("page5.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Five_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        viewpage5.getChildren().add(label);
        display1.getChildren().setAll(viewpage5);
    }

    @FXML
    public void pagesix() throws IOException {
        Pane viewpage6 = FXMLLoader.load(getClass().getResource("page6.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Six_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        viewpage6.getChildren().add(label);
        display1.getChildren().setAll(viewpage6);
    }
    //admin login
    @FXML
    TextField adminpass;
    //admin login password logic and launch of admin page
    public void adminlogin(ActionEvent event) throws IOException {

        String Varadminpass = adminpass.getText();
        if (Varadminpass.equals("123")) {

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("adminpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

        } else {
            adminpass.clear();
            invalid_user_pass.setText("Invalid Username Or Password");
        }
    }

    //button for loginpage to admin
    public void logtoadmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("adminlogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 360);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    //button for adminlogin page to mainpage
    public void adminlogtomain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 360);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    //Open search Page
    public Stage searchbox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("search.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        Stage searchboxstage = new Stage();
        searchboxstage.setScene(scene);
        searchboxstage.show();
        return searchboxstage;
    }


    //searchbar data
    public void searchvalue(String selectednode) throws IOException {
        switch (selectednode){
            case "[education and awareness-rising]":
                page1();
                break;
            case "[human and institutional capacity]":
                page2();
                break;
            case "[mitigation planning]":
                page3();
                break;
            case "[adaptation planning]":
                page4();
                break;
            case "[impact reduction]":
                page5();
                break;
            case "[early warning]":
                page6();
                break;
        }
    }
    public void page1() throws IOException{
        Pane pane = FXMLLoader.load(getClass().getResource("page1.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_One_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    public void page2()throws IOException{
        Pane pane = FXMLLoader.load(getClass().getResource("page2.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Two_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    public void page3()throws IOException{
        Pane pane = FXMLLoader.load(getClass().getResource("page3.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Three_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    public void page4()throws IOException{
        Pane pane = FXMLLoader.load(getClass().getResource("page4.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Four_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    public void page5()throws IOException{
        Pane pane = FXMLLoader.load(getClass().getResource("page5.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Five_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    public void page6()throws IOException{
        Pane pane = FXMLLoader.load(getClass().getResource("page6.fxml"));
        BufferedReader reader = new BufferedReader(new FileReader("Page_Six_Data.txt"));
        String temp = reader.readLine();
        Label label = new Label(temp);
        label.setPrefWidth(910);
        label.setPrefHeight(490);
        label.setLayoutX(17);
        label.setLayoutY(135);
        label.setWrapText(true);
        label.setFont(Font.font("Arial", FontWeight.BOLD,14));
        label.setAlignment(Pos.TOP_LEFT);
        pane.getChildren().add(label);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void sharepage()throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Copylink.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 100);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    Label successcopied;

    public void copy_to_clip(){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString("https://sdgs.un.org/goals/goal13");
        clipboard.setContent(content);
        successcopied.setText("Successfully Copied!!!");
    }
}
