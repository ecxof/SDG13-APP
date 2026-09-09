package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Topic;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class AdminEditorController {

    private static final int MAX_CHARS = 300;

    @FXML private FontIcon topicIcon;
    @FXML private Label topicTitle;
    @FXML private Label topicSub;
    @FXML private Label charCounter;
    @FXML private TextArea infoArea;
    @FXML private Label saveStatus;

    private Topic topic;
    private String originalText = "";

    public void setTopic(Topic topic) {
        this.topic = topic;
        topicIcon.setIconLiteral(topic.iconLiteral());
        topicTitle.setText("Page " + topic.id() + " — " + topic.title());
        topicSub.setText(topic.subtitle());
        originalText = DataStore.read(topic);
        infoArea.setText(originalText);
        updateCounter(originalText.length());
        saveStatus.setText("");

        infoArea.textProperty().addListener((ChangeListener<String>) (obs, oldV, newV) -> {
            updateCounter(newV == null ? 0 : newV.length());
            saveStatus.setText("");
        });
    }

    private void updateCounter(int len) {
        charCounter.setText(len + " / " + MAX_CHARS);
        charCounter.getStyleClass().remove("over-limit");
        if (len > MAX_CHARS) charCounter.getStyleClass().add("over-limit");
    }

    @FXML
    private void save() {
        String text = infoArea.getText() == null ? "" : infoArea.getText();
        if (text.length() > MAX_CHARS) {
            setStatus("Too long — trim to " + MAX_CHARS + " characters.", false);
            return;
        }
        try {
            DataStore.write(topic, text);
            originalText = text;
            setStatus("✓ Saved", true);
        } catch (IOException e) {
            setStatus("✗ Save failed: " + e.getMessage(), false);
        }
    }

    @FXML
    private void discard() {
        infoArea.setText(originalText);
        updateCounter(originalText.length());
        setStatus("Reverted", true);
    }

    private void setStatus(String text, boolean ok) {
        saveStatus.setText(text);
        saveStatus.getStyleClass().removeAll("status-ok", "status-err");
        saveStatus.getStyleClass().add(ok ? "status-ok" : "status-err");
    }
}
