
module com.example.dimadiary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires org.json;
    requires okhttp;
    requires disk.restapi.sdk;
    requires google.api.client;

    opens com.example.dimadiary to javafx.fxml;
    exports com.example.dimadiary;
}
