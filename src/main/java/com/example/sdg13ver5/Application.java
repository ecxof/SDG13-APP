package com.example.sdg13ver5;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    // TODO: replace with real authentication.
    public static final String USER_NAME = "123";
    public static final String USER_PASS = "123";
    public static final String ADMIN_PASS = "123";

    public static final String APP_STYLES = "/com/example/sdg13ver5/styles.css";

    @Override
    public void start(Stage stage) throws IOException {
        ThemeManager.apply();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 600);
        addAppStylesheet(scene);

        stage.setTitle("SDG-13 — Climate Action");
        stage.setScene(scene);
        stage.setMinWidth(880);
        stage.setMinHeight(560);
        stage.centerOnScreen();
        stage.show();
    }

    public static void addAppStylesheet(Scene scene) {
        String url = Objects.requireNonNull(
                Application.class.getResource(APP_STYLES),
                "styles.css not found on classpath"
        ).toExternalForm();
        if (!scene.getStylesheets().contains(url)) {
            scene.getStylesheets().add(url);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
