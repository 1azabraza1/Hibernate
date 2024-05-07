package jm.task.core.jdbc;

import antlr.Utils;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    private static final UserServiceImpl userService = new UserServiceImpl();

    private static final User u1 = new User("Azamat", "Bermagambetov", (byte) 22);
    private static final User u2 = new User("Vladimir", "Putin", (byte) 65);
    private static final User u3 = new User("Chingis", "Khan", (byte) 54);
    private static final User u4 = new User("Your", "Mom", (byte) 100);
    static User[] users = new User[] {u1, u2, u3, u4};

    public static void main(String[] args) {

        userService.createUsersTable();
        for (User u : users) {
            userService.saveUser(u.getName(), u.getLastName(), u.getAge());
        }
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
