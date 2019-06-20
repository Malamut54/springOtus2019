package ru.otus.boot.hw3.service;

import ru.otus.boot.hw3.model.User;

public interface UserService {
    User createUser(String firstName, String secondName);
}
