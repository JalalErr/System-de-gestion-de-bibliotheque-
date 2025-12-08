module com.example.gestion_electronic_documents {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;


    opens com.usermanager to javafx.fxml;
    exports com.usermanager;
}