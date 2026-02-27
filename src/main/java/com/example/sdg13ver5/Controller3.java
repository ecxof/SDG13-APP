package com.example.sdg13ver5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller3 {
    @FXML
    Pane display2;
    @FXML
    Pane adminNavRail;
    @FXML
    Button adminNavToggleBtn;
    @FXML
    Button adminpageone;
    @FXML
    Button adminpagetwo;
    @FXML
    Button adminpagethree;
    @FXML
    Button adminpagefour;
    @FXML
    Button adminpagefive;
    @FXML
    Button adminpagesix;
    @FXML
    Button adminExitBtn;

    private static final String[] ADMIN_ICONS = { "🌱", "📢", "👥", "🔄", "🔰", "🔔" };
    private static final String[] ADMIN_FULL = { "🌱  Page 1 - Improve Education", "📢  Page 2 - Awareness-Raising",
            "👥  Page 3 - Human Impact",
            "🔄  Page 4 - Adaptation", "🔰  Page 5 - Impact Reduction", "🔔  Page 6 - Early Warning" };

    private Stage stage;
    private Scene scene;
    private boolean navCollapsed = false;
    private Button activeNavButton = null;

    // CSS stylesheet path for reuse
    private String getStylesheet() {
        return Application.class.getResource("styles.css").toExternalForm();
    }

    // Button To switch back to Login Page
    public void admintomain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 880, 530);
        scene.getStylesheets().add(getStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    // Button To Switch Between Admin Pages
    @FXML
    public void adminone() throws IOException {
        setActiveNav(adminpageone);
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage1.fxml"));
        display2.getChildren().setAll(viewpage);
    }

    @FXML
    public void admintwo() throws IOException {
        setActiveNav(adminpagetwo);
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage2.fxml"));
        display2.getChildren().setAll(viewpage);
    }

    @FXML
    public void adminthree() throws IOException {
        setActiveNav(adminpagethree);
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage3.fxml"));
        display2.getChildren().setAll(viewpage);
    }

    @FXML
    public void adminfour() throws IOException {
        setActiveNav(adminpagefour);
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage4.fxml"));
        display2.getChildren().setAll(viewpage);
    }

    @FXML
    public void adminfive() throws IOException {
        setActiveNav(adminpagefive);
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage5.fxml"));
        display2.getChildren().setAll(viewpage);
    }

    @FXML
    public void adminsix() throws IOException {
        setActiveNav(adminpagesix);
        Pane viewpage = FXMLLoader.load(getClass().getResource("adminpage6.fxml"));
        display2.getChildren().setAll(viewpage);
    }

    @FXML
    public void toggleAdminNav() {
        Button[] buttons = { adminpageone, adminpagetwo, adminpagethree, adminpagefour, adminpagefive, adminpagesix };

        if (navCollapsed) {
            adminNavRail.setPrefWidth(240);
            if (adminNavToggleBtn != null) {
                adminNavToggleBtn.setPrefWidth(240);
                adminNavToggleBtn.setPrefHeight(52);
                adminNavToggleBtn.setAlignment(Pos.CENTER_LEFT);
                adminNavToggleBtn.setPadding(new Insets(0, 0, 0, 12));
            }
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i] != null) {
                    buttons[i].setPrefWidth(240);
                    buttons[i].setPrefHeight(44);
                    buttons[i].setText(ADMIN_FULL[i]);
                    buttons[i].setAlignment(Pos.BASELINE_LEFT);
                    buttons[i].setPadding(new Insets(0, 0, 0, 12));
                }
            }
            if (adminExitBtn != null) {
                adminExitBtn.setPrefWidth(240);
                adminExitBtn.setPrefHeight(44);
                adminExitBtn.setText("❌  Exit To Main");
                adminExitBtn.setAlignment(Pos.BASELINE_LEFT);
                adminExitBtn.setPadding(new Insets(0, 0, 0, 12));
            }
            navCollapsed = false;
        } else {
            adminNavRail.setPrefWidth(64);
            if (adminNavToggleBtn != null) {
                adminNavToggleBtn.setPrefWidth(64);
                adminNavToggleBtn.setPrefHeight(52);
                adminNavToggleBtn.setAlignment(Pos.CENTER);
                adminNavToggleBtn.setPadding(Insets.EMPTY);
            }
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i] != null) {
                    buttons[i].setPrefWidth(64);
                    buttons[i].setPrefHeight(44);
                    buttons[i].setText(ADMIN_ICONS[i]);
                    buttons[i].setAlignment(Pos.CENTER);
                    buttons[i].setPadding(Insets.EMPTY);
                }
            }
            if (adminExitBtn != null) {
                adminExitBtn.setPrefWidth(64);
                adminExitBtn.setPrefHeight(44);
                adminExitBtn.setText("❌");
                adminExitBtn.setAlignment(Pos.CENTER);
                adminExitBtn.setPadding(Insets.EMPTY);
            }
            navCollapsed = true;
        }
    }

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
}
