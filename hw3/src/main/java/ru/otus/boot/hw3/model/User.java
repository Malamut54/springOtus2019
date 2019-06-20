package ru.otus.boot.hw3.model;

public class User {
    private final String firstName;
    private final String secondName;

    public User(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }
}
