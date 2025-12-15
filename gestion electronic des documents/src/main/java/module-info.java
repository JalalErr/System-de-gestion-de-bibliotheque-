module com.example.gestion_electronic_documents {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;


    opens com.usermanager.controller to javafx.fxml;

    opens com.usermanager to javafx.fxml;
    exports com.usermanager;
}