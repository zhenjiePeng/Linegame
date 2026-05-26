package controller;

import service.UserService;

public class LoginController {
    private UserService userService;

    public LoginController() {
        userService = new UserService();
    }

    public boolean login(String username, String password) {
        // UserService.login 返回 User 对象，成功则非 null
        return userService.login(username, password) != null;
    }

    public boolean register(String username, String password) {
        return userService.register(username, password);
    }
}