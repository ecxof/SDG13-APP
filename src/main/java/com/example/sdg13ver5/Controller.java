package com.example.sdg13ver5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        return instance;
    }

    public static void setInstance(Controller ctrl) {
        instance = ctrl;
    }

    private Stage stage;
    private Scene scene;

    // Tracks whether the password toggle is showing plain text
    private boolean passwordShown = false;
    private boolean adminPasswordShown = false;
    // Tracks whether nav is collapsed
    private boolean navCollapsed = false;
    // Tracks currently active nav button
    private Button activeNavButton = null;

    // CSS stylesheet path for reuse
    private String getStylesheet() {
        return getClass().getResource("styles.css").toExternalForm();
    }

    // ===== Login fields =====
    @FXML
    TextField Username;
    @FXML
    PasswordField Password;
    @FXML
    TextField PasswordVisible;
    @FXML
    Button togglePasswordBtn;
    @FXML
    Label Invalid_User_Pass;

    // ===== Admin login fields =====
    @FXML
    PasswordField adminpass;
    @FXML
    TextField adminpassVisible;
    @FXML
    Button toggleAdminPasswordBtn;

    @FXML
    private Pane navRail;
    @FXML
    Button navToggleBtn;
    @FXML
    Button searchBtn;
    @FXML
    Button shareBtn;
    @FXML
    Button feedbackBtn;
    @FXML
    Button exitBtn;

    // ===== Nav page buttons =====

    @FXML
    public void login(ActionEvent event) throws IOException {
        String Varusername = Username.getText();
        String Varpass = getPasswordText();

        boolean hasError = false;
        if (Varusername.isEmpty()) {
            Username.getStyleClass().add("field-error");
            hasError = true;
        } else {
            Username.getStyleClass().remove("field-error");
        }
        if (Varpass.isEmpty()) {
            Password.getStyleClass().add("field-error");
            PasswordVisible.getStyleClass().add("field-error");
            hasError = true;
        } else {
            Password.getStyleClass().remove("field-error");
            PasswordVisible.getStyleClass().remove("field-error");
        }

        if (hasError) {
            Invalid_User_Pass.setText("Please fill in all fields");
            return;
        }

        if (Varusername.equals("123") && Varpass.equals("123")) {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 1280, 720);
            scene.getStylesheets().add(getStylesheet());

            // Set shared instance for other controllers (like search)
            setInstance(fxmlLoader.getController());

            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } else {
            Username.clear();
            Password.clear();
            PasswordVisible.clear();
            Invalid_User_Pass.setText("Invalid username or password");
        }
    }

    // Main page to exit
    public void exit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 880, 530);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // Main page to feedback
    public void tofeedback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("feedbackpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // button for feedback page to postfeedback
    public void topostfeedback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("postfeedback.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // ---------------------------------------------------------------
    // PASSWORD TOGGLE
    // ---------------------------------------------------------------
    @FXML
    public void togglePassword() {
        if (passwordShown) {
            Password.setText(PasswordVisible.getText());
            PasswordVisible.setVisible(false);
            Password.setVisible(true);
            togglePasswordBtn.setText("\u25CE");
            passwordShown = false;
        } else {
            PasswordVisible.setText(Password.getText());
            Password.setVisible(false);
            PasswordVisible.setVisible(true);
            togglePasswordBtn.setText("\u25CF");
            passwordShown = true;
        }
    }

    @FXML
    public void toggleAdminPassword() {
        if (adminPasswordShown) {
            adminpass.setText(adminpassVisible.getText());
            adminpassVisible.setVisible(false);
            adminpass.setVisible(true);
            toggleAdminPasswordBtn.setText("\u25CE");
            adminPasswordShown = false;
        } else {
            adminpassVisible.setText(adminpass.getText());
            adminpass.setVisible(false);
            adminpassVisible.setVisible(true);
            toggleAdminPasswordBtn.setText("\u25CF");
            adminPasswordShown = true;
        }
    }

    private String getPasswordText() {
        return passwordShown ? PasswordVisible.getText() : Password.getText();
    }

    private String getAdminPasswordText() {
        return adminPasswordShown ? adminpassVisible.getText() : adminpass.getText();
    }

    // ---------------------------------------------------------------
    // NAV TOGGLE
    // ---------------------------------------------------------------
    @FXML
    public void toggleNav() {
        Button[] pageButtons = { button1, button2, button3, button4, button5, button6 };

        if (navCollapsed) {
            navRail.setPrefWidth(240);
            if (navToggleBtn != null) {
                navToggleBtn.setPrefWidth(240);
                navToggleBtn.setPrefHeight(52);
                navToggleBtn.setAlignment(Pos.CENTER_LEFT);
                navToggleBtn.setPadding(new Insets(0, 0, 0, 12));
            }
            for (int i = 0; i < pageButtons.length; i++) {
                if (pageButtons[i] != null) {
                    pageButtons[i].setPrefWidth(240);
                    pageButtons[i].setPrefHeight(52);
                    pageButtons[i].setText(NAV_FULL[i]);
                    pageButtons[i].setAlignment(Pos.BASELINE_LEFT);
                    pageButtons[i].setPadding(new Insets(0, 0, 0, 12));
                }
            }
            if (searchBtn != null) {
                searchBtn.setPrefWidth(240);
                searchBtn.setPrefHeight(52);
                searchBtn.setText("\uD83D\uDD0D  Search");
                searchBtn.setAlignment(Pos.BASELINE_LEFT);
                searchBtn.setPadding(new Insets(0, 0, 0, 12));
            }
            if (shareBtn != null) {
                shareBtn.setPrefWidth(240);
                shareBtn.setPrefHeight(52);
                shareBtn.setText("\uD83D\uDD17  Share");
                shareBtn.setAlignment(Pos.BASELINE_LEFT);
                shareBtn.setPadding(new Insets(0, 0, 0, 12));
            }
            if (feedbackBtn != null) {
                feedbackBtn.setPrefWidth(240);
                feedbackBtn.setPrefHeight(52);
                feedbackBtn.setText("\uD83D\uDCAC  Feedback");
                feedbackBtn.setAlignment(Pos.BASELINE_LEFT);
                feedbackBtn.setPadding(new Insets(0, 0, 0, 12));
            }
            if (exitBtn != null) {
                exitBtn.setPrefWidth(240);
                exitBtn.setPrefHeight(52);
                exitBtn.setText("\u274C  Exit");
                exitBtn.setAlignment(Pos.BASELINE_LEFT);
                exitBtn.setPadding(new Insets(0, 0, 0, 12));
            }
            navCollapsed = false;
        } else {
            navRail.setPrefWidth(64);
            if (navToggleBtn != null) {
                navToggleBtn.setPrefWidth(64);
                navToggleBtn.setPrefHeight(52);
                navToggleBtn.setAlignment(Pos.CENTER);
                navToggleBtn.setPadding(Insets.EMPTY);
            }
            for (int i = 0; i < pageButtons.length; i++) {
                if (pageButtons[i] != null) {
                    pageButtons[i].setPrefWidth(64);
                    pageButtons[i].setPrefHeight(52);
                    pageButtons[i].setText(NAV_ICONS[i]);
                    pageButtons[i].setAlignment(Pos.CENTER);
                    pageButtons[i].setPadding(Insets.EMPTY);
                }
            }
            if (searchBtn != null) {
                searchBtn.setPrefWidth(64);
                searchBtn.setPrefHeight(52);
                searchBtn.setText("\uD83D\uDD0D");
                searchBtn.setAlignment(Pos.CENTER);
                searchBtn.setPadding(Insets.EMPTY);
            }
            if (shareBtn != null) {
                shareBtn.setPrefWidth(64);
                shareBtn.setPrefHeight(52);
                shareBtn.setText("\uD83D\uDD17");
                shareBtn.setAlignment(Pos.CENTER);
                shareBtn.setPadding(Insets.EMPTY);
            }
            if (feedbackBtn != null) {
                feedbackBtn.setPrefWidth(64);
                feedbackBtn.setPrefHeight(52);
                feedbackBtn.setText("\uD83D\uDCAC");
                feedbackBtn.setAlignment(Pos.CENTER);
                feedbackBtn.setPadding(Insets.EMPTY);
            }
            if (exitBtn != null) {
                exitBtn.setPrefWidth(64);
                exitBtn.setPrefHeight(52);
                exitBtn.setText("\u274C");
                exitBtn.setAlignment(Pos.CENTER);
                exitBtn.setPadding(Insets.EMPTY);
            }
            navCollapsed = true;
        }
    }

    private static final String[] NAV_ICONS = { "🌱", "📢", "👥", "🔄", "🔰", "🔔" };
    private static final String[] NAV_FULL = { "🌱  Improve Education", "📢  Awareness-Raising", "👥  Human Impact",
            "🔄  Adaptation", "🔰  Impact Reduction", "🔔  Early Warning" };

    private void setActiveNav(Button btn) {
        if (activeNavButton != null) {
            activeNavButton.getStyleClass().remove("nav-button-active");
        }
        if (btn != null) {
            if (!btn.getStyleClass().contains("nav-button-active")) {
                btn.getStyleClass().add("nav-button-active");
            }
            activeNavButton = btn;
        }
    }

    // feedback page to main page
    public void feedtomain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().add(getStylesheet());
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
            showError("Error loading " + fxmlFile + ": " + e.getMessage());
        }
    }

    /**
     * Shows an error message in display1 so failures are visible during
     * development.
     */
    private void showError(String message) {
        Label err = new Label(message);
        err.setWrapText(true);
        err.setStyle("-fx-text-fill: red; -fx-font-size: 14px; -fx-padding: 40; -fx-font-family: Arial;");
        display1.getChildren().setAll(err);
    }

    // ---------------------------------------------------------------
    // PASSWORD TOGGLE (Admin Login)
    // ---------------------------------------------------------------

    // admin login password logic and launch of admin page
    public void adminlogin(ActionEvent event) throws IOException {
        String Varadminpass = getAdminPasswordText();

        if (Varadminpass.isEmpty()) {
            adminpass.getStyleClass().add("field-error");
            adminpassVisible.getStyleClass().add("field-error");
            Invalid_User_Pass.setText("Please enter a password");
            return;
        }

        if (Varadminpass.equals("123")) {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("adminpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 1280, 720);
            scene.getStylesheets().add(getStylesheet());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } else {
            adminpass.clear();
            adminpassVisible.clear();
            adminpass.getStyleClass().add("field-error");
            adminpassVisible.getStyleClass().add("field-error");
            Invalid_User_Pass.setText("Invalid password");
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
        scene.getStylesheets().add(getStylesheet());
        Stage searchboxstage = new Stage();
        searchboxstage.setScene(scene);
        searchboxstage.setTitle("Search");
        searchboxstage.show();
        return searchboxstage;
    }

    // searchbar data
    public void searchvalue(String selectednode) throws IOException {
        switch (selectednode) {
            case "[education and awareness-rising]":
                pageone();
                break;
            case "[human and institutional capacity]":
                pagetwo();
                break;
            case "[mitigation planning]":
                pagethree();
                break;
            case "[adaptation planning]":
                pagefour();
                break;
            case "[impact reduction]":
                pagefive();
                break;
            case "[early warning]":
                pagesix();
                break;
        }
    }

    public void pageone() {
        setActiveNav(button1);
        loadPage("page1.fxml", "Page_One_Data.txt");
    }

    public void pagetwo() {
        setActiveNav(button2);
        loadPage("page2.fxml", "Page_Two_Data.txt");
    }

    public void pagethree() {
        setActiveNav(button3);
        loadPage("page3.fxml", "Page_Three_Data.txt");
    }

    public void pagefour() {
        setActiveNav(button4);
        loadPage("page4.fxml", "Page_Four_Data.txt");
    }

    public void pagefive() {
        setActiveNav(button5);
        loadPage("page5.fxml", "Page_Five_Data.txt");
    }

    public void pagesix() {
        setActiveNav(button6);
        loadPage("page6.fxml", "Page_Six_Data.txt");
    }

    public void sharepage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Copylink.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 100);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setTitle("Share link");
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
        successcopied.setText("Successfully copied!");
    }
}
