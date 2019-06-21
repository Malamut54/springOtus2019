package ru.otus.boot.hw4.service;

import ru.otus.boot.hw4.model.User;

public interface UserService {
    User createUser(String firstName, String secondName);
}
