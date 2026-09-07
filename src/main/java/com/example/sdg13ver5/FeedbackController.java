package com.example.sdg13ver5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FeedbackController {

    @FXML private TextField nameField;
    @FXML private TextArea suggestionArea;
    @FXML private Label errorLabel;

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("mainshell.fxml"));
        Scene scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
        Application.addAppStylesheet(scene);
        stage.setScene(scene);
    }

    @FXML
    private void submit(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String sugg = suggestionArea.getText();
        if (name == null || name.isBlank() || sugg == null || sugg.isBlank()) {
            errorLabel.setText("Please add both your name and a suggestion.");
            return;
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("postfeedback.fxml"));
        Scene scene = new Scene(loader.load(), stage.getWidth(), stage.getHeight());
        Application.addAppStylesheet(scene);
        stage.setScene(scene);
    }
}
