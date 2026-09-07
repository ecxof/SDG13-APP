package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Topic;
import com.example.sdg13ver5.model.Topics;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class AdminShellController {

    @FXML private VBox navRail;
    @FXML private VBox navContainer;
    @FXML private VBox navFooter;
    @FXML private Button navToggleBtn;
    @FXML private Label pageTitle;
    @FXML private Button themeBtn;
    @FXML private FontIcon themeIcon;
    @FXML private StackPane contentHost;

    private NavRail rail;

    @FXML
    public void initialize() {
        HBox brand = (HBox) navRail.getChildren().get(0);
        VBox brandText = (VBox) brand.getChildren().get(1);
        rail = new NavRail(navRail, brand, brandText);

        for (Topic t : Topics.ALL) {
            Button b = rail.addTopicButton(t, this::edit);
            navContainer.getChildren().add(b);
        }

        navFooter.getChildren().add(
                rail.addActionButton("fth-log-out", "Exit to login", true, this::exit)
        );

        updateThemeIcon();
        edit(Topics.ALL.get(0));
    }

    private void edit(Topic topic) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("admineditor.fxml"));
            Parent view = loader.load();
            AdminEditorController c = loader.getController();
            c.setTopic(topic);
            contentHost.getChildren().setAll(view);
            pageTitle.setText("Editing: " + topic.title());
            int i = topic.id() - 1;
            if (i >= 0 && i < navContainer.getChildren().size()
                    && navContainer.getChildren().get(i) instanceof Button b) {
                rail.setActive(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void toggleNav() {
        rail.toggleCollapsed();
    }

    @FXML
    private void toggleTheme() {
        ThemeManager.toggle();
        updateThemeIcon();
    }

    private void updateThemeIcon() {
        themeIcon.setIconLiteral(ThemeManager.isDark() ? "fth-sun" : "fth-moon");
    }

    private void exit() {
        try {
            Stage stage = (Stage) navRail.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("start.fxml"));
            Scene scene = new Scene(loader.load(), 960, 600);
            Application.addAppStylesheet(scene);
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
