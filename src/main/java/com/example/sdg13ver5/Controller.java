package com.example.sdg13ver5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {

    private Stage stage;
    private Scene scene;

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label invalid_user_pass;

    // Login
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

    // Main page to exit
    public void exit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 880, 530);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // Main page to feedback
    public void tofeedback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("feedbackpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1040, 624);
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

    // Feedback page to Thank you page
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

    // feedback page to main page
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

    // display Pages – AnchorPane so pages fill full height (background images
    // extend to top)
    @FXML
    AnchorPane display1;

    @FXML
    VBox navRail;

    @FXML
    public void toggleNav() {
        if (navRail != null) {
            boolean collapsed = navRail.getPrefWidth() < 50;
            navRail.setPrefWidth(collapsed ? 190.0 : 0.0);
            navRail.setMinWidth(collapsed ? 190.0 : 0.0);
            navRail.setMaxWidth(collapsed ? 190.0 : 0.0);
        }
    }

    /**
     * Helper: loads a page FXML, wires up bodyLabel from a text file, stretches it
     * to fill display1.
     */
    private void loadPage(String fxmlFile, String dataFile) {
        try {
            java.net.URL fxmlUrl = getClass().getResource(fxmlFile);
            if (fxmlUrl == null) {
                showError("FXML not found: " + fxmlFile);
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            AnchorPane page = loader.load();

            // Populate the bodyLabel from the data file
            Label bodyLabel = (Label) loader.getNamespace().get("bodyLabel");
            if (bodyLabel != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (sb.length() > 0)
                            sb.append("\n");
                        sb.append(line);
                    }
                    bodyLabel.setText(sb.toString());
                } catch (Exception e) {
                    // Data file missing – page still displays without body text
                }
            }

            // Stretch page to fill display1 via AnchorPane constraints
            AnchorPane.setTopAnchor(page, 0.0);
            AnchorPane.setBottomAnchor(page, 0.0);
            AnchorPane.setLeftAnchor(page, 0.0);
            AnchorPane.setRightAnchor(page, 0.0);

            display1.getChildren().setAll(page);

        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            if (e.getCause() != null)
                msg += "\nCause: " + e.getCause().getMessage();
            showError("Error loading " + fxmlFile + ":\n" + msg);
        }
    }

    /**
     * Shows an error message in display1 so failures are visible during
     * development.
     */
    private void showError(String message) {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-background-color: white; -fx-padding: 40;");
        Label header = new Label(message);
        header.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
        header.setWrapText(true);
        display1.getChildren().setAll(vbox);
        vbox.getChildren().add(header);

        // Add more space for the full path if visible in message
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
    }

    @FXML
    public void pageone() {
        loadPage("page1.fxml", "Page_One_Data.txt");
    }

    @FXML
    public void pagetwo() {
        loadPage("page2.fxml", "Page_Two_Data.txt");
    }

    @FXML
    public void pagethree() {
        loadPage("page3.fxml", "Page_Three_Data.txt");
    }

    @FXML
    public void pagefour() {
        loadPage("page4.fxml", "Page_Four_Data.txt");
    }

    @FXML
    public void pagefive() {
        loadPage("page5.fxml", "Page_Five_Data.txt");
    }

    @FXML
    public void pagesix() {
        loadPage("page6.fxml", "Page_Six_Data.txt");
    }

    // admin login
    @FXML
    TextField adminpass;

    // admin login password logic and launch of admin page
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

    // button for loginpage to admin
    public void logtoadmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("adminlogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 880, 530);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // button for adminlogin page to mainpage
    public void adminlogtomain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 880, 530);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // Open search Page
    public Stage searchbox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("search.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        Stage searchboxstage = new Stage();
        searchboxstage.setScene(scene);
        searchboxstage.show();
        return searchboxstage;
    }

    // searchbar data
    public void searchvalue(String selectednode) throws IOException {
        switch (selectednode) {
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

    /** Helper: opens a page in a new popup window (used by search). */
    private void openPageInWindow(String fxmlFile, String dataFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        AnchorPane page = loader.load();

        Label bodyLabel = (Label) loader.getNamespace().get("bodyLabel");
        if (bodyLabel != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (sb.length() > 0)
                        sb.append("\n");
                    sb.append(line);
                }
                bodyLabel.setText(sb.toString());
            } catch (IOException e) {
                // Data file missing – page still displays
            }
        }

        Scene scene = new Scene(page, 1040, 624);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void page1() throws IOException {
        openPageInWindow("page1.fxml", "Page_One_Data.txt");
    }

    public void page2() throws IOException {
        openPageInWindow("page2.fxml", "Page_Two_Data.txt");
    }

    public void page3() throws IOException {
        openPageInWindow("page3.fxml", "Page_Three_Data.txt");
    }

    public void page4() throws IOException {
        openPageInWindow("page4.fxml", "Page_Four_Data.txt");
    }

    public void page5() throws IOException {
        openPageInWindow("page5.fxml", "Page_Five_Data.txt");
    }

    public void page6() throws IOException {
        openPageInWindow("page6.fxml", "Page_Six_Data.txt");
    }

    public void sharepage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Copylink.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 100);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    Label successcopied;

    public void copy_to_clip() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString("https://sdgs.un.org/goals/goal13");
        clipboard.setContent(content);
        successcopied.setText("Successfully Copied!!!");
    }
}
