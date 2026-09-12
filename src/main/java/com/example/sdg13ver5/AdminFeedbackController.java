package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Feedback;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminFeedbackController {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());

    @FXML private Label pathLabel;
    @FXML private Label countLabel;
    @FXML private TableView<Feedback> table;
    @FXML private TableColumn<Feedback, String> whenCol;
    @FXML private TableColumn<Feedback, String> nameCol;
    @FXML private TableColumn<Feedback, String> suggestionCol;

    @FXML
    public void initialize() {
        whenCol.setCellValueFactory(cd ->
                new ReadOnlyStringWrapper(FMT.format(cd.getValue().submittedAt())));
        nameCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().name()));
        suggestionCol.setCellValueFactory(cd -> new ReadOnlyStringWrapper(cd.getValue().suggestion()));
        suggestionCol.setCellFactory(col -> {
            javafx.scene.control.TableCell<Feedback, String> cell = new javafx.scene.control.TableCell<>() {
                private final javafx.scene.control.Label label = new javafx.scene.control.Label();
                { label.setWrapText(true); setGraphic(label); setPrefHeight(javafx.scene.control.Control.USE_COMPUTED_SIZE); }
                @Override protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) { setGraphic(null); }
                    else { label.setText(item); setGraphic(label); }
                }
            };
            return cell;
        });
        pathLabel.setText("Stored at: " + FeedbackStore.path());
        refresh();
    }

    @FXML
    public void refresh() {
        List<Feedback> all = FeedbackStore.readAll();
        // newest first
        all.sort((a, b) -> b.submittedAt().compareTo(a.submittedAt()));
        table.setItems(FXCollections.observableArrayList(all));
        countLabel.setText(all.size() + " submission" + (all.size() == 1 ? "" : "s"));
    }
}
