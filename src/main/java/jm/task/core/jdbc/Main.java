package jm.task.core.jdbc;

import antlr.Utils;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        User u1 = new User("Azamat", "Bermagambetov", (byte) 22);
        User u2 = new User("Vladimir", "Putin", (byte) 65);
        User u3 = new User("Chingis", "Khan", (byte) 54);
        User u4 = new User("Your", "Mom", (byte) 100);
        User[] users = new User[] {u1, u2, u3, u4};

        userService.createUsersTable();
        for (User u : users) {
            userService.saveUser(u.getName(), u.getLastName(), u.getAge());
        }
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
