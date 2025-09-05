module com.example.dimadiary {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires org.json;
    requires okhttp;
    requires disk.restapi.sdk;

    opens com.example.dimadiary to javafx.fxml;
    exports com.example.dimadiary;
}