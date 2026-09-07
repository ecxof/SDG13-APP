package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Topic;
import com.example.sdg13ver5.model.Topics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class MainShellController {

    @FXML private VBox navRail;
    @FXML private VBox navContainer;
    @FXML private VBox navFooter;
    @FXML private Button navToggleBtn;
    @FXML private Label pageTitle;
    @FXML private Button searchBtn;
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
            Button b = rail.addTopicButton(t, this::showTopic);
            navContainer.getChildren().add(b);
        }

        navFooter.getChildren().addAll(
                rail.addActionButton("fth-share-2", "Share", false, this::openShare),
                rail.addActionButton("fth-message-square", "Feedback", false, this::openFeedback),
                rail.addActionButton("fth-log-out", "Exit", true, this::exit)
        );

        updateThemeIcon();
        showTopic(Topics.ALL.get(0));
    }

    private void showTopic(Topic topic) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("page.fxml"));
            Parent view = loader.load();
            PageController pc = loader.getController();
            pc.setTopic(topic);
            contentHost.getChildren().setAll(view);
            pageTitle.setText(topic.title());
            // highlight matching nav button
            int i = topic.id() - 1;
            if (i >= 0 && i < navContainer.getChildren().size()
                    && navContainer.getChildren().get(i) instanceof Button b) {
                rail.setActive(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateBySearch(Topic topic) {
        showTopic(topic);
    }

    @FXML
    private void toggleNav() {
        rail.toggleCollapsed();
    }

    @FXML
    private void openSearch() {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("search.fxml"));
            Parent root = loader.load();
            SearchController sc = loader.getController();
            sc.setNavigator(this::navigateBySearch);

            Stage popup = new Stage();
            popup.initOwner(navRail.getScene().getWindow());
            popup.initModality(Modality.WINDOW_MODAL);
            popup.setTitle("Search");
            Scene scene = new Scene(root);
            Application.addAppStylesheet(scene);
            popup.setScene(scene);
            sc.setStage(popup);
            popup.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openShare() {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("Copylink.fxml"));
            Parent root = loader.load();
            Stage popup = new Stage();
            popup.initOwner(navRail.getScene().getWindow());
            popup.initModality(Modality.WINDOW_MODAL);
            popup.setTitle("Share");
            Scene scene = new Scene(root);
            Application.addAppStylesheet(scene);
            popup.setScene(scene);
            popup.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFeedback() {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("feedbackpage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) navRail.getScene().getWindow();
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            Application.addAppStylesheet(scene);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @FXML
    private void toggleTheme() {
        ThemeManager.toggle();
        updateThemeIcon();
    }

    private void updateThemeIcon() {
        themeIcon.setIconLiteral(ThemeManager.isDark() ? "fth-sun" : "fth-moon");
    }
}
