package com.example.sdg13ver5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller {
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
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    TextField passwordVisible;
    @FXML
    Button togglePasswordBtn;
    @FXML
    Label invalid_user_pass;

    // ===== Admin login fields =====
    @FXML
    PasswordField adminpass;
    @FXML
    TextField adminpassVisible;
    @FXML
    Button toggleAdminPasswordBtn;

    // ===== Nav rail =====
    @FXML
    private BorderPane mainRoot;
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

    // Icon-only texts (for collapsed 64px state)
    private static final String[] NAV_ICONS = { "\uD83D\uDCDA", "\uD83D\uDCE2", "\uD83D\uDC65", "\uD83D\uDD04",
            "\uD83D\uDEE1", "\u26A0" };
    // Full icon + label texts (for expanded 240px state)
    private static final String[] NAV_FULL = {
            "\uD83D\uDCDA  Improve education",
            "\uD83D\uDCE2  Awareness-raising",
            "\uD83D\uDC65  Human impact",
            "\uD83D\uDD04  Adaptation",
            "\uD83D\uDEE1  Impact reduction",
            "\u26A0  Early warning"
    };

    @FXML
    AnchorPane display1;
    @FXML
    AnchorPane headerBar;

    // ===== Feedback fields =====
    @FXML
    TextField feedsugg;
    @FXML
    TextField feedname;
    @FXML
    Label feed_label;

    // ===== Share =====
    @FXML
    Label successcopied;

    // ---------------------------------------------------------------
    // PASSWORD TOGGLE (User Login)
    // ---------------------------------------------------------------
    @FXML
    public void togglePassword() {
        if (passwordShown) {
            // Hide: copy text from visible field to password field
            password.setText(passwordVisible.getText());
            passwordVisible.setVisible(false);
            password.setVisible(true);
            // Open eye — password hidden
            togglePasswordBtn.setText("\u25CE");
            passwordShown = false;
        } else {
            // Show: copy text from password field to visible field
            passwordVisible.setText(password.getText());
            password.setVisible(false);
            passwordVisible.setVisible(true);
            // Closed eye — password visible
            togglePasswordBtn.setText("\u25CF");
            passwordShown = true;
        }
    }

    // ---------------------------------------------------------------
    // PASSWORD TOGGLE (Admin Login)
    // ---------------------------------------------------------------
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

    // Helper to get actual password text regardless of toggle state
    private String getPasswordText() {
        return passwordShown ? passwordVisible.getText() : password.getText();
    }

    private String getAdminPasswordText() {
        return adminPasswordShown ? adminpassVisible.getText() : adminpass.getText();
    }

    // ---------------------------------------------------------------
    // LOGIN
    // ---------------------------------------------------------------
    @FXML
    public void login(ActionEvent event) throws IOException {
        String Varusername = username.getText();
        String Varpass = getPasswordText();

        // Inline validation: highlight empty fields
        boolean hasError = false;
        if (Varusername.isEmpty()) {
            username.getStyleClass().add("field-error");
            hasError = true;
        } else {
            username.getStyleClass().remove("field-error");
        }
        if (Varpass.isEmpty()) {
            password.getStyleClass().add("field-error");
            passwordVisible.getStyleClass().add("field-error");
            hasError = true;
        } else {
            password.getStyleClass().remove("field-error");
            passwordVisible.getStyleClass().remove("field-error");
        }

        if (hasError) {
            invalid_user_pass.setText("Please fill in all fields");
            return;
        }

        if (Varusername.equals("123") && Varpass.equals("123")) {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 1280, 720);
            scene.getStylesheets().add(getStylesheet());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } else {
            username.clear();
            password.clear();
            passwordVisible.clear();
            invalid_user_pass.setText("Invalid username or password");
        }
    }

    // ---------------------------------------------------------------
    // NAV TOGGLE (Collapsible rail: 240px ↔ 64px)
    // ---------------------------------------------------------------
    @FXML
    public void toggleNav() {
        Button[] pageButtons = { button1, button2, button3, button4, button5, button6 };

        if (navCollapsed) {
            // --- EXPAND ---
            navRail.setPrefWidth(240);
            // Restore full icon+label text
            for (int i = 0; i < pageButtons.length; i++) {
                if (pageButtons[i] != null) {
                    pageButtons[i].setPrefWidth(240);
                    pageButtons[i].setText(NAV_FULL[i]);
                }
            }
            if (searchBtn != null) {
                searchBtn.setPrefWidth(240);
                searchBtn.setText("\uD83D\uDD0D  Search");
            }
            if (shareBtn != null) {
                shareBtn.setPrefWidth(240);
                shareBtn.setText("\uD83D\uDD17  Share");
            }
            if (feedbackBtn != null) {
                feedbackBtn.setPrefWidth(240);
                feedbackBtn.setText("\uD83D\uDCAC  Feedback");
            }
            if (exitBtn != null) {
                exitBtn.setPrefWidth(240);
                exitBtn.setText("\u274C  Exit");
            }
            navCollapsed = false;
        } else {
            // --- COLLAPSE ---
            navRail.setPrefWidth(64);
            // Show icon only
            for (int i = 0; i < pageButtons.length; i++) {
                if (pageButtons[i] != null) {
                    pageButtons[i].setPrefWidth(64);
                    pageButtons[i].setText(NAV_ICONS[i]);
                }
            }
            if (searchBtn != null) {
                searchBtn.setPrefWidth(64);
                searchBtn.setText("\uD83D\uDD0D");
            }
            if (shareBtn != null) {
                shareBtn.setPrefWidth(64);
                shareBtn.setText("\uD83D\uDD17");
            }
            if (feedbackBtn != null) {
                feedbackBtn.setPrefWidth(64);
                feedbackBtn.setText("\uD83D\uDCAC");
            }
            if (exitBtn != null) {
                exitBtn.setPrefWidth(64);
                exitBtn.setText("\u274C");
            }
            navCollapsed = true;
        }
    }

    // ---------------------------------------------------------------
    // NAV ACTIVE STATE
    // ---------------------------------------------------------------
    private void setActiveNav(Button btn) {
        // Remove active from previous
        if (activeNavButton != null) {
            activeNavButton.getStyleClass().remove("nav-button-active");
        }
        // Apply active to current
        if (btn != null) {
            if (!btn.getStyleClass().contains("nav-button-active")) {
                btn.getStyleClass().add("nav-button-active");
            }
            activeNavButton = btn;
        }
    }

    // ---------------------------------------------------------------
    // EXIT (Main page → Login)
    // ---------------------------------------------------------------
    public void exit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 360);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // ---------------------------------------------------------------
    // FEEDBACK
    // ---------------------------------------------------------------
    public void tofeedback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("feedbackpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 540);
        scene.getStylesheets().add(getStylesheet());
        // Esc to close (go back to main)
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                try {
                    feedtomain(event);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // Feedback page to Thank you page
    public void topostfeedback(ActionEvent event) throws IOException {
        String varfeedsugg = feedsugg.getText();
        String varfeedname = feedname.getText();

        // Inline validation
        boolean hasError = false;
        if (varfeedname.isEmpty()) {
            feedname.getStyleClass().add("field-error");
            hasError = true;
        } else {
            feedname.getStyleClass().remove("field-error");
        }
        if (varfeedsugg.isEmpty()) {
            feedsugg.getStyleClass().add("field-error");
            hasError = true;
        } else {
            feedsugg.getStyleClass().remove("field-error");
        }

        if (hasError) {
            feed_label.setText("Please fill in all fields");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("postfeedback.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 600, 500);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // Feedback page to main page
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

    // Close feedback (for × button)
    @FXML
    public void closeFeedback(ActionEvent event) throws IOException {
        feedtomain(event);
    }

    // ---------------------------------------------------------------
    // CONSTANTS
    // ---------------------------------------------------------------
    private static final int MAX_CHARS = 300;

    /**
     * Truncates text to maxLen characters, appending "…" if trimmed.
     */
    private String truncateText(String text, int maxLen) {
        if (text == null)
            return "";
        text = text.trim();
        if (text.length() <= maxLen)
            return text;
        return text.substring(0, maxLen) + "…";
    }

    /**
     * Reads the first line from a data file safely.
     * Returns empty string if file doesn't exist or is empty.
     */
    private String readDataFile(String filename) {
        File file = new File(filename);
        if (!file.exists())
            return "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            return (line != null) ? line : "";
        } catch (IOException e) {
            System.err.println("Error reading " + filename + ": " + e.getMessage());
            return "";
        }
    }

    /**
     * Injects text into a card's bodyLabel (looked up by fx:id).
     */
    private void injectCardText(Pane pageRoot, String text) {
        String truncated = truncateText(text, MAX_CHARS);
        Label bodyLabel = (Label) pageRoot.lookup("#bodyLabel");
        if (bodyLabel != null) {
            bodyLabel.setText(truncated);
        }
    }

    // ---------------------------------------------------------------
    // DISPLAY PAGES (in main page display pane)
    // ---------------------------------------------------------------
    @FXML
    public void pageone() {
        try {
            setActiveNav(button1);
            Pane viewpage1 = FXMLLoader.load(getClass().getResource("page1.fxml"));
            String text = readDataFile("Page_One_Data.txt");
            injectCardText(viewpage1, text);
            display1.getChildren().setAll(viewpage1);
            setupResponsivePage(viewpage1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading page one: " + e.getMessage());
        }
    }

    /**
     * Helper to make loaded FXML pages responsive and properly clipped.
     */
    private void setupResponsivePage(Pane pageRoot) {
        if (pageRoot == null || display1 == null)
            return;

        // 1. Force page to fill display1 using AnchorPane anchors
        AnchorPane.setTopAnchor(pageRoot, 0.0);
        AnchorPane.setRightAnchor(pageRoot, 0.0);
        AnchorPane.setBottomAnchor(pageRoot, 0.0);
        AnchorPane.setLeftAnchor(pageRoot, 0.0);

        // 2. Bind all ImageViews inside the page to also stretch (Hero backgrounds)
        for (Node node : pageRoot.getChildren()) {
            if (node instanceof ImageView iv) {
                iv.fitWidthProperty().bind(pageRoot.widthProperty());
                iv.fitHeightProperty().bind(pageRoot.heightProperty());
            }
        }

        // 3. Set clipping on display1 to prevent content bleeding into header/sidebar
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(display1.widthProperty());
        clip.heightProperty().bind(display1.heightProperty());
        display1.setClip(clip);
    }

    @FXML
    public void pagetwo() throws IOException {
        setActiveNav(button2);
        Pane viewpage2 = FXMLLoader.load(getClass().getResource("page2.fxml"));
        injectCardText(viewpage2, readDataFile("Page_Two_Data.txt"));
        display1.getChildren().setAll(viewpage2);
        setupResponsivePage(viewpage2);
    }

    @FXML
    public void pagethree() throws IOException {
        setActiveNav(button3);
        Pane viewpage3 = FXMLLoader.load(getClass().getResource("page3.fxml"));
        injectCardText(viewpage3, readDataFile("Page_Three_Data.txt"));
        display1.getChildren().setAll(viewpage3);
        setupResponsivePage(viewpage3);
    }

    @FXML
    public void pagefour() throws IOException {
        setActiveNav(button4);
        Pane viewpage4 = FXMLLoader.load(getClass().getResource("page4.fxml"));
        injectCardText(viewpage4, readDataFile("Page_Four_Data.txt"));
        display1.getChildren().setAll(viewpage4);
        setupResponsivePage(viewpage4);
    }

    @FXML
    public void pagefive() throws IOException {
        setActiveNav(button5);
        Pane viewpage5 = FXMLLoader.load(getClass().getResource("page5.fxml"));
        injectCardText(viewpage5, readDataFile("Page_Five_Data.txt"));
        display1.getChildren().setAll(viewpage5);
        setupResponsivePage(viewpage5);
    }

    @FXML
    public void pagesix() throws IOException {
        setActiveNav(button6);
        Pane viewpage6 = FXMLLoader.load(getClass().getResource("page6.fxml"));
        injectCardText(viewpage6, readDataFile("Page_Six_Data.txt"));
        display1.getChildren().setAll(viewpage6);
        setupResponsivePage(viewpage6);
    }

    // ---------------------------------------------------------------
    // ADMIN LOGIN
    // ---------------------------------------------------------------
    @FXML
    public void adminlogin(ActionEvent event) throws IOException {
        String Varadminpass = getAdminPasswordText();

        // Inline validation
        if (Varadminpass.isEmpty()) {
            adminpass.getStyleClass().add("field-error");
            adminpassVisible.getStyleClass().add("field-error");
            invalid_user_pass.setText("Please enter a password");
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
            invalid_user_pass.setText("Invalid password");
        }
    }

    // Button for loginpage to admin
    public void logtoadmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("adminlogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 360);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // Button for adminlogin page to mainpage (start page)
    public void adminlogtomain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 640, 360);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // ---------------------------------------------------------------
    // SEARCH
    // ---------------------------------------------------------------
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

    // Search bar data
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

    // Pages opened from search (in new windows)
    public void page1() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("page1.fxml"));
        injectCardText(pane, readDataFile("Page_One_Data.txt"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void page2() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("page2.fxml"));
        injectCardText(pane, readDataFile("Page_Two_Data.txt"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void page3() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("page3.fxml"));
        injectCardText(pane, readDataFile("Page_Three_Data.txt"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void page4() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("page4.fxml"));
        injectCardText(pane, readDataFile("Page_Four_Data.txt"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void page5() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("page5.fxml"));
        injectCardText(pane, readDataFile("Page_Five_Data.txt"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void page6() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("page6.fxml"));
        injectCardText(pane, readDataFile("Page_Six_Data.txt"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    // ---------------------------------------------------------------
    // SHARE
    // ---------------------------------------------------------------
    public void sharepage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Copylink.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 100);
        scene.getStylesheets().add(getStylesheet());
        Stage stage = new Stage();
        stage.setTitle("Share link");
        stage.setScene(scene);
        stage.show();
    }

    public void copy_to_clip() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString("https://sdgs.un.org/goals/goal13");
        clipboard.setContent(content);
        successcopied.setText("Successfully copied!");
    }
}
