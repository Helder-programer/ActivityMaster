package controllers;

import models.user.User;
import models.user.UserDAO;

public class UserController {
    public User login(String username, String password) throws Exception {
        User user = new User(username, password);
        UserDAO userDAO = new UserDAO();

        if (userDAO.authenticate(user)) {
            return user;
        }

        return null;
    }
}
