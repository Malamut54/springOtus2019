package service;

import model.User;

public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String firstName, String secondName) {
        return new User(firstName, secondName);
    }
}
