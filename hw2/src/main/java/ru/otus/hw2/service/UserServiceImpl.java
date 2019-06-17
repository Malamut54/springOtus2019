package ru.otus.hw2.service;

import ru.otus.hw2.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName, String secondName) {
        return new User(firstName, secondName);
    }
}
