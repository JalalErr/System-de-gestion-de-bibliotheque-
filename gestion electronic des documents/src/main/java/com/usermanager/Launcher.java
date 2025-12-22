package com.usermanager;

import com.usermanager.util.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application{
    @Override
    public void start(Stage stage)  throws Exception {
        if (!DatabaseConnection.getInstance().testConnection()) {
            System.err.println("Failed to connect to database!");
            showErrorAlert("Database Connection Failed",
                    "Could not connect to the database. Please check your configuration.");
            return;
        }

        System.out.println("✓ Database connection successful");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));

        Scene scene = new Scene(loader.load(), 600, 400);
        //stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    private void showErrorAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
