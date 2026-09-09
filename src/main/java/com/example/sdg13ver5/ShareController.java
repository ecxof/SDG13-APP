package com.example.sdg13ver5;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.util.Duration;

public class ShareController {

    private static final String SDG13_URL = "https://sdgs.un.org/goals/goal13";

    @FXML private TextField urlField;
    @FXML private Label successLabel;

    @FXML
    public void initialize() {
        urlField.setText(SDG13_URL);
    }

    @FXML
    private void copyToClipboard() {
        ClipboardContent content = new ClipboardContent();
        content.putString(SDG13_URL);
        Clipboard.getSystemClipboard().setContent(content);
        successLabel.setText("✓ Link copied to clipboard");

        PauseTransition p = new PauseTransition(Duration.seconds(2.5));
        p.setOnFinished(e -> successLabel.setText(""));
        p.play();
    }
}
