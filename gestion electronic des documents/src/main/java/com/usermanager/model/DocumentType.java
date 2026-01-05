package com.usermanager.model;

public enum DocumentType {
    LIVRE ("Livre"),
    RAPPORT("Rapport");


    private final String displayName;

    DocumentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
