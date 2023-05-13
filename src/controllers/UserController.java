package controllers;

import models.user.User;

public class UserController {
    public User login(String username, String password) throws Exception {
        User user = new User(username, password);

        if (User.authenticate(user)) {
            return user;
        }
        return null;
    }

    public void register(User user) throws Exception {
        user.save();
    }
}
