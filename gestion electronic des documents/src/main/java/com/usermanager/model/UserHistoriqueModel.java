package com.usermanager.model;

import java.time.LocalDateTime;

public class UserHistoriqueModel {

    private int id;
    private int userId;
    private String action;
    private String description;
    private LocalDateTime DateAction;


    public UserHistoriqueModel() {}

    public UserHistoriqueModel(int id, int userId, String action, String description, LocalDateTime dateAction) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.description = description;
        DateAction = dateAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateAction() {
        return DateAction;
    }

    public void setDateAction(LocalDateTime dateAction) {
        DateAction = dateAction;
    }
}
