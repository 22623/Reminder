package com.example.reminder;

import java.util.HashMap;
import java.util.Map;

public class LoginManager {
    private static Map<String, User> users;


    private static Map<String, User> getUsers() {
        if (users == null) {
            users = new HashMap<>();
            users.put("22623@stu.ipbeja.pt", new User(1, "22623@stu.ipbeja.pt", "22623"));
            users.put("22644@stu.ipbeja.pt", new User(2, "22644@stu.ipbeja.pt", "22644"));
            users.put("",new User(0,"",""));
        }
        return users;
    }

    public static User validateUser(String username, String password) {
        User user = getUsers().get(username);
        if (user == null) return null;
        return user.getPassword().equals(password) ? user : null;
//        if (user.getPassword().equals(password)) {
//            return user;
//        } else {
//            return null;
//        }
    }
}
