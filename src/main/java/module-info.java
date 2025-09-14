module com.example.dimadiary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires org.json;
    requires disk.restapi.sdk;
    requires google.api.client;
    requires com.fasterxml.jackson.core;
    requires java.sql;

    opens com.example.dimadiary to javafx.fxml;
    exports com.example.dimadiary;
}