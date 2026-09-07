module com.example.sdg13ver5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    requires atlantafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;

    requires java.prefs;

    opens com.example.sdg13ver5 to javafx.fxml;

    exports com.example.sdg13ver5;
    exports com.example.sdg13ver5.model;
}
