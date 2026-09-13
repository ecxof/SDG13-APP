package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Quizzes;
import com.example.sdg13ver5.model.Topic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.function.Consumer;

public class PageController {

    @FXML private VBox hero;
    @FXML private FontIcon heroIcon;
    @FXML private Label heroEyebrow;
    @FXML private Label heroTitle;
    @FXML private Label heroSub;
    @FXML private Label cardHeading;
    @FXML private Label bodyLabel;
    @FXML private Label quizStatus;
    @FXML private Button startQuizBtn;

    private Topic topic;
    private Consumer<Topic> onStartQuiz;

    public void setTopic(Topic topic, Consumer<Topic> onStartQuiz) {
        this.topic = topic;
        this.onStartQuiz = onStartQuiz;
        heroIcon.setIconLiteral(topic.iconLiteral());
        heroEyebrow.setText("SDG-13 · " + String.format("%02d", topic.id()));
        heroTitle.setText(topic.title());
        heroSub.setText(topic.subtitle());
        cardHeading.setText(topic.cardHeading());
        bodyLabel.setText(DataStore.read(topic));

        int total = Quizzes.forTopic(topic.id()).size();
        int best = ProgressStore.bestScore(topic.id());
        if (total == 0) {
            quizStatus.setText("No quiz available for this topic.");
            startQuizBtn.setDisable(true);
        } else if (best < 0) {
            quizStatus.setText(total + " questions · not attempted yet.");
        } else {
            quizStatus.setText("Best score: " + best + " / " + total
                    + (best == total ? " · perfect!" : " · try again to improve."));
        }

        hero.setStyle("-fx-background-color: linear-gradient(to right, "
                + topic.accentColor() + ", -sdg-accent);");
    }

    @FXML
    private void startQuiz() {
        if (onStartQuiz != null && topic != null) onStartQuiz.accept(topic);
    }
}
