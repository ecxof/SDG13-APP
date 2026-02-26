package com.example.sdg13ver5;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Controller2 implements Initializable {

    // Array For Storing Data For SearchBar
    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("education and awareness-rising",
                    "human and institutional capacity",
                    "mitigation planning",
                    "adaptation planning",
                    "impact reduction",
                    "early warning"));
    @FXML
    Stage stage;

    @FXML
    Scene scene;

    Controller controller = new Controller();

    @FXML
    private TextField searchbar2;

    @FXML
    private ListView<String> listview;

    // Get Text And Clear Text From SearchBar
    @FXML
    public void search() {
        listview.getItems().clear();
        listview.getItems().addAll(searchList(searchbar2.getText(), words));
    }

    // Handle Mouse Click Event And Retrieve Data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listview.getItems().addAll(words);
        listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    String selectednode = String.valueOf(listview.getSelectionModel().getSelectedItems());
                    try {
                        controller.searchvalue(selectednode);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

    // Search Logic
    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

}
