package com.usermanager.controller;

import com.usermanager.model.AdminModel;
import com.usermanager.service.UserSession;


public class SideBarMenuController {
    public void goToProfile () {
        System.out.println("Redirection ----> profle");
        //SceneManager.switchScene("profile/ProfileUser","Admin Profile");
        SceneManager.switchScene("profile/ProfileAdmin.fxml", "Profile Admin");

    }
    public void logout () {
        AdminModel user = UserSession.getUser();
        UserSession.logout(user);
        SceneManager.switchScene("LoginPage.fxml", "Login");
    }
    public void goToAddUser () {
        SceneManager.switchScene("UsersManager/AddUsersForme.fxml", "Profile Admin");
    }
}
