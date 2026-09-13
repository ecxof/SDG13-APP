package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Question;
import com.example.sdg13ver5.model.Quizzes;
import com.example.sdg13ver5.model.Topic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizController {

    @FXML private FontIcon topicIcon;
    @FXML private Label titleLabel;
    @FXML private Label subLabel;
    @FXML private VBox questionsBox;
    @FXML private Label resultLabel;
    @FXML private Button submitBtn;
    @FXML private Button retryBtn;

    private Topic topic;
    private List<Question> questions;
    private final List<ToggleGroup> groups = new ArrayList<>();
    private Runnable onBack;

    public void setContext(Topic topic, Runnable onBack) {
        this.topic = topic;
        this.onBack = onBack;
        this.questions = Quizzes.forTopic(topic.id());
        topicIcon.setIconLiteral(topic.iconLiteral());
        titleLabel.setText("Quiz — " + topic.title());
        int best = ProgressStore.bestScore(topic.id());
        if (best >= 0 && !questions.isEmpty()) {
            subLabel.setText("Best score so far: " + best + " / " + questions.size());
        } else {
            subLabel.setText(questions.size() + " questions · test your understanding.");
        }
        buildQuestions();
    }

    private void buildQuestions() {
        questionsBox.getChildren().clear();
        groups.clear();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            VBox block = new VBox(8);
            Label prompt = new Label((i + 1) + ". " + q.prompt());
            prompt.getStyleClass().add("card-heading");
            prompt.setStyle("-fx-font-size: 15px;");
            prompt.setWrapText(true);
            block.getChildren().add(prompt);

            ToggleGroup g = new ToggleGroup();
            groups.add(g);
            for (int c = 0; c < q.choices().size(); c++) {
                RadioButton rb = new RadioButton(q.choices().get(c));
                rb.setToggleGroup(g);
                rb.setUserData(c);
                rb.setWrapText(true);
                block.getChildren().add(rb);
            }
            Label explain = new Label();
            explain.getStyleClass().add("muted");
            explain.setWrapText(true);
            explain.setVisible(false);
            explain.setManaged(false);
            block.getChildren().add(explain);
            block.getProperties().put("explain", explain);

            questionsBox.getChildren().add(block);
        }
    }

    @FXML
    private void submit() {
        int correct = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            VBox block = (VBox) questionsBox.getChildren().get(i);
            RadioButton selected = (RadioButton) groups.get(i).getSelectedToggle();
            Label explain = (Label) block.getProperties().get("explain");

            int chosen = selected == null ? -1 : (int) selected.getUserData();
            boolean isCorrect = q.isCorrect(chosen);
            if (isCorrect) correct++;

            for (int c = 0; c < q.choices().size(); c++) {
                RadioButton rb = (RadioButton) block.getChildren().get(c + 1);
                rb.setDisable(true);
                rb.getStyleClass().removeAll("status-ok", "status-err");
                if (c == q.correctIndex()) rb.getStyleClass().add("status-ok");
                else if (c == chosen) rb.getStyleClass().add("status-err");
            }
            explain.setText((isCorrect ? "✓ " : "✗ ") + q.explanation());
            explain.getStyleClass().removeAll("status-ok", "status-err");
            explain.getStyleClass().add(isCorrect ? "status-ok" : "status-err");
            explain.setVisible(true);
            explain.setManaged(true);
        }

        int total = questions.size();
        resultLabel.setText("Score: " + correct + " / " + total);
        resultLabel.getStyleClass().removeAll("status-ok", "status-err");
        resultLabel.getStyleClass().add(correct == total ? "status-ok" : "status-err");

        submitBtn.setVisible(false);
        submitBtn.setManaged(false);
        retryBtn.setVisible(true);
        retryBtn.setManaged(true);

        try {
            ProgressStore.recordScore(topic.id(), correct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retry() {
        resultLabel.setText("");
        submitBtn.setVisible(true);
        submitBtn.setManaged(true);
        retryBtn.setVisible(false);
        retryBtn.setManaged(false);
        buildQuestions();
    }

    @FXML
    private void back() {
        if (onBack != null) onBack.run();
    }
}
