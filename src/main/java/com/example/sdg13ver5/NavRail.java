package com.example.sdg13ver5;

import com.example.sdg13ver5.model.Topic;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Small helper for building and highlighting nav rail buttons from a list of
 * items. Used by both the user and admin shells.
 */
public final class NavRail {

    public static final double EXPANDED_WIDTH = 240;
    public static final double COLLAPSED_WIDTH = 72;

    private final VBox rail;
    private final HBox brandBox;
    private final VBox brandText;
    private final List<Button> buttons = new ArrayList<>();
    private boolean collapsed = false;

    public NavRail(VBox rail, HBox brandBox, VBox brandText) {
        this.rail = rail;
        this.brandBox = brandBox;
        this.brandText = brandText;
    }

    public Button addTopicButton(Topic topic, Consumer<Topic> onClick) {
        Button b = makeButton(topic.iconLiteral(), topic.title(), false);
        b.setOnAction(e -> onClick.accept(topic));
        buttons.add(b);
        return b;
    }

    public Button addActionButton(String icon, String label, boolean danger, Runnable onClick) {
        Button b = makeButton(icon, label, danger);
        b.setOnAction(e -> onClick.run());
        return b;
    }

    private Button makeButton(String icon, String label, boolean danger) {
        FontIcon i = new FontIcon(icon);
        Button b = new Button(label, i);
        b.getStyleClass().add("nav-button");
        if (danger) b.getStyleClass().add("danger");
        b.setMaxWidth(Double.MAX_VALUE);
        b.getProperties().put("nav-label", label);
        return b;
    }

    public void setActive(Button active) {
        for (Button b : buttons) b.getStyleClass().remove("active");
        if (active != null && !active.getStyleClass().contains("active")) {
            active.getStyleClass().add("active");
        }
    }

    public boolean toggleCollapsed() {
        collapsed = !collapsed;
        rail.setPrefWidth(collapsed ? COLLAPSED_WIDTH : EXPANDED_WIDTH);
        rail.setMinWidth(collapsed ? COLLAPSED_WIDTH : EXPANDED_WIDTH);
        brandText.setVisible(!collapsed);
        brandText.setManaged(!collapsed);
        for (Node n : railButtons(rail)) {
            if (n instanceof Button b && b.getProperties().containsKey("nav-label")) {
                b.setText(collapsed ? "" : (String) b.getProperties().get("nav-label"));
            }
        }
        return collapsed;
    }

    private static List<Node> railButtons(VBox rail) {
        List<Node> out = new ArrayList<>();
        collect(rail, out);
        return out;
    }

    private static void collect(Node node, List<Node> out) {
        if (node instanceof Button) { out.add(node); return; }
        if (node instanceof javafx.scene.Parent p) {
            for (Node child : p.getChildrenUnmodifiable()) collect(child, out);
        }
    }

    public static Region spacer() {
        Region r = new Region();
        VBox.setVgrow(r, javafx.scene.layout.Priority.ALWAYS);
        return r;
    }

    public static Label subheader(String text) {
        Label l = new Label(text);
        l.getStyleClass().add("nav-brand-sub");
        VBox.setMargin(l, new javafx.geometry.Insets(12, 12, 4, 12));
        return l;
    }
}
