package com.usermanager.service;

import com.usermanager.model.UserModel;
import com.usermanager.model.UserRole;

public class UserSession {
    private static UserModel currentUser;

    private UserSession() {}

    public static void login(UserModel user) {
        currentUser = user;
    }

    public static void logout(UserModel user) {
        currentUser = null;
    }

    public static UserModel getUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static boolean isAdmin() {
        return isLoggedIn() && currentUser.getRole() == UserRole.ADMIN;
    }


}
