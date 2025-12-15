package com.usermanager.controller;

import javafx.event.ActionEvent;

import java.io.*;
import java.util.*;

public class DashboarsController {
    public void dash() {
        System.out.println("hello");
        //SceneManager.switchScene("login.fxml");
    }
    public void gologinpage(ActionEvent event) throws IOException {
        switchScene.switchScene(event, "/views/login.fxml", "Login");
    }

}
