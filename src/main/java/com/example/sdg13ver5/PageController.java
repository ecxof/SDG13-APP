package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Topic;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class PageController {

    @FXML private VBox hero;
    @FXML private FontIcon heroIcon;
    @FXML private Label heroEyebrow;
    @FXML private Label heroTitle;
    @FXML private Label heroSub;
    @FXML private Label cardHeading;
    @FXML private Label bodyLabel;

    public void setTopic(Topic topic) {
        heroIcon.setIconLiteral(topic.iconLiteral());
        heroEyebrow.setText("SDG-13 · " + String.format("%02d", topic.id()));
        heroTitle.setText(topic.title());
        heroSub.setText(topic.subtitle());
        cardHeading.setText(topic.cardHeading());
        bodyLabel.setText(DataStore.read(topic));

        hero.setStyle("-fx-background-color: linear-gradient(to right, "
                + topic.accentColor() + ", -sdg-accent);");
    }
}
