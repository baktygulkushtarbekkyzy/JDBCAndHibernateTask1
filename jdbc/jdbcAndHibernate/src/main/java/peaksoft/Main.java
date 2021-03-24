package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJdbcImpl();
        userDao.saveUser("Baktygul", "Kushtarbek kyzy", (byte) 21);
        userDao.removeUserById(1);
        userDao.createUsersTable();
        userDao.dropUsersTable();
        userDao.cleanUsersTable();
        for (User us : userDao.getAllUsers()) {
            System.out.println(us.getId());
            System.out.println(us.getName());
            System.out.println(us.getLastName());
            System.out.println(us.getAge());

        }
    }
}
