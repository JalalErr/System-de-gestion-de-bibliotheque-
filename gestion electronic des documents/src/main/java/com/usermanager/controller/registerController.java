package com.usermanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class registerController {

    @FXML public TextField username;
    public void gologinpage(ActionEvent event) throws IOException {
        switchScene.switchScene(event, "/views/login.fxml", "Login");
    }

}
