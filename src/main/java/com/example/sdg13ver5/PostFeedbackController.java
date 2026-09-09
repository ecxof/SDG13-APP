package com.example.sdg13ver5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PostFeedbackController {

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("mainshell.fxml"));
        Scene scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
        Application.addAppStylesheet(scene);
        stage.setScene(scene);
    }
}
