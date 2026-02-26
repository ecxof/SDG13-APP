package com.example.sdg13ver5;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller4 implements Initializable {

    private static final int MAX_CHARS = 300;

    @FXML
    TextField admintitle1;

    @FXML
    TextArea Info1;
    @FXML
    TextArea Info2;
    @FXML
    TextArea Info3;
    @FXML
    TextArea Info4;
    @FXML
    TextArea Info5;
    @FXML
    TextArea Info6;

    // Character counter labels (one per page)
    @FXML
    Label charCounter1;
    @FXML
    Label charCounter2;
    @FXML
    Label charCounter3;
    @FXML
    Label charCounter4;
    @FXML
    Label charCounter5;
    @FXML
    Label charCounter6;

    // Save status labels (one per page)
    @FXML
    Label saveStatus1;
    @FXML
    Label saveStatus2;
    @FXML
    Label saveStatus3;
    @FXML
    Label saveStatus4;
    @FXML
    Label saveStatus5;
    @FXML
    Label saveStatus6;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Attach live character counters to each TextArea
        attachCounter(Info1, charCounter1, saveStatus1);
        attachCounter(Info2, charCounter2, saveStatus2);
        attachCounter(Info3, charCounter3, saveStatus3);
        attachCounter(Info4, charCounter4, saveStatus4);
        attachCounter(Info5, charCounter5, saveStatus5);
        attachCounter(Info6, charCounter6, saveStatus6);
    }

    /**
     * Binds a live character counter and clear-on-type behavior to a TextArea.
     */
    private void attachCounter(TextArea area, Label counter, Label status) {
        if (area == null || counter == null)
            return;
        // Set initial value
        updateCounter(area, counter, status);
        // Listen for changes
        area.textProperty().addListener((obs, oldVal, newVal) -> {
            updateCounter(area, counter, status);
        });
    }

    private void updateCounter(TextArea area, Label counter, Label status) {
        int len = (area.getText() == null) ? 0 : area.getText().length();
        counter.setText(len + " / " + MAX_CHARS);

        // Clear status message when user types
        if (status != null) {
            status.setText("");
        }

        if (len > MAX_CHARS) {
            counter.setStyle("-fx-text-fill: #B91C1C; -fx-font-size: 13px; -fx-font-weight: bold;");
        } else {
            counter.setStyle("-fx-text-fill: #6B7280; -fx-font-size: 13px; -fx-font-weight: normal;");
        }
    }

    /**
     * Validates length and saves text to file, or shows error.
     */
    private boolean saveToFile(TextArea area, Label status, String filename) {
        String text = area.getText();
        if (text == null)
            text = "";

        if (text.length() > MAX_CHARS) {
            status.setText("⚠ Too long! Max " + MAX_CHARS + " characters.");
            status.setStyle("-fx-text-fill: #B91C1C; -fx-font-size: 13px; -fx-font-weight: bold;");
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(text);
            status.setText("✓ Saved successfully");
            status.setStyle("-fx-text-fill: #166534; -fx-font-size: 13px; -fx-font-weight: bold;");
            return true;
        } catch (IOException e) {
            status.setText("✗ Error saving file");
            status.setStyle("-fx-text-fill: #B91C1C; -fx-font-size: 13px; -fx-font-weight: bold;");
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void page_1() {
        saveToFile(Info1, saveStatus1, "Page_One_Data.txt");
    }

    @FXML
    public void page_2() {
        saveToFile(Info2, saveStatus2, "Page_Two_Data.txt");
    }

    @FXML
    public void page_3() {
        saveToFile(Info3, saveStatus3, "Page_Three_Data.txt");
    }

    @FXML
    public void page_4() {
        saveToFile(Info4, saveStatus4, "Page_Four_Data.txt");
    }

    @FXML
    public void page_5() {
        saveToFile(Info5, saveStatus5, "Page_Five_Data.txt");
    }

    @FXML
    public void page_6() {
        saveToFile(Info6, saveStatus6, "Page_Six_Data.txt");
    }
}
