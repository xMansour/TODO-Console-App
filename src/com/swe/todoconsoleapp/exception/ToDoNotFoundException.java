package com.swe.todoconsoleapp.exception;

public class ToDoNotFoundException extends Exception {
    public ToDoNotFoundException(String message) {
        super(message);
    }
}
