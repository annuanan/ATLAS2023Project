package com.amazon.busPassManagement.controller;

public class DuplicatePassException extends Exception {
    public DuplicatePassException() {
        super("You have already applied for a bus pass on this route.");
    }
}