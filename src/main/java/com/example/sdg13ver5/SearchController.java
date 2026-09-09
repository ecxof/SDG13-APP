package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Topic;
import com.example.sdg13ver5.model.Topics;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class SearchController {

    @FXML private TextField queryField;
    @FXML private ListView<Topic> resultsList;

    private Stage stage;
    private Consumer<Topic> navigator;

    public void setStage(Stage stage) { this.stage = stage; }
    public void setNavigator(Consumer<Topic> navigator) { this.navigator = navigator; }

    @FXML
    public void initialize() {
        resultsList.setItems(FXCollections.observableArrayList(Topics.ALL));
        resultsList.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Topic item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.id() + ". " + item.title() + " — " + item.subtitle());
                }
            }
        });
        if (!resultsList.getItems().isEmpty()) resultsList.getSelectionModel().selectFirst();

        queryField.textProperty().addListener((ChangeListener<String>) (obs, o, n) -> {
            resultsList.setItems(FXCollections.observableArrayList(Topics.filter(n)));
            if (!resultsList.getItems().isEmpty()) resultsList.getSelectionModel().selectFirst();
        });

        queryField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) chooseSelected();
            else if (e.getCode() == KeyCode.ESCAPE) close();
            else if (e.getCode() == KeyCode.DOWN) resultsList.requestFocus();
        });

        resultsList.setOnMouseClicked(e -> { if (e.getClickCount() >= 2) chooseSelected(); });
        resultsList.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) chooseSelected();
            else if (e.getCode() == KeyCode.ESCAPE) close();
        });
    }

    private void chooseSelected() {
        Topic t = resultsList.getSelectionModel().getSelectedItem();
        if (t != null && navigator != null) navigator.accept(t);
        close();
    }

    private void close() { if (stage != null) stage.close(); }
}
