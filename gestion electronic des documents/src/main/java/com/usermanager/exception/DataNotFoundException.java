package com.usermanager.exception;

public class DataNotFoundException extends DAOException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
