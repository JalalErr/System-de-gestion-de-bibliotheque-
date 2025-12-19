package com.usermanager.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class switchScene {
    public static void switchScene(ActionEvent event, String file, String title) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(switchScene.class.getResource(file)));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}

class SceneManager {

    public static void switchScene(String fxml, String title) {
        try {
            Parent root = FXMLLoader.load(
                    Objects.requireNonNull(SceneManager.class.getResource("/views/" + fxml)));
            Stage stage = (Stage) Stage.getWindows().getFirst();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

