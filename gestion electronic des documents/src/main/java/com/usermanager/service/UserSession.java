package com.usermanager.service;

import com.usermanager.model.AdminModel;
import com.usermanager.model.UserRole;

public class UserSession {
    private static AdminModel currentUser;

    private UserSession() {}

    public static void login(AdminModel user) {
        currentUser = user;
    }

    public static void logout(AdminModel user) {
        currentUser = null;
    }

    public static AdminModel getUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static boolean isAdmin() {
        return isLoggedIn() && currentUser.getRole() == UserRole.ADMIN;
    }


}
