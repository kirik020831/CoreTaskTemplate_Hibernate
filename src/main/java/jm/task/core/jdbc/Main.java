package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService user = new UserServiceImpl();

    public static void main(String[] args) {
        user.createUsersTable();
        user.saveUser("Антон", "Патрушев", (byte) 23);
        user.saveUser("Никита", "Савичев", (byte) 31);
        user.saveUser("Илья", "Власов", (byte) 53);
        user.saveUser("Петр", "Маркович", (byte) 61);
        user.removeUserById(3);
        for (User allUser : user.getAllUsers()) {
            System.out.println(allUser);
        }
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
