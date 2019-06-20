package ru.otus.boot.hw3.service;

import org.springframework.stereotype.Service;
import ru.otus.boot.hw3.model.User;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName, String secondName) {
        return new User(firstName, secondName);
    }
}
