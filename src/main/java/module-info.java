module com.example.sdg13ver5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    requires org.controlsfx.controls;

    opens com.example.sdg13ver5 to javafx.fxml;

    exports com.example.sdg13ver5;
}