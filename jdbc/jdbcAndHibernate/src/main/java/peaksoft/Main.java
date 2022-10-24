package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.createUsersTable();
//        userDaoHibernate.saveUser("Baktygul","Kushtarberovna",(byte)21);
//        userDaoHibernate.removeUserById(1);
        userDaoHibernate.getAllUsers();
//        userDaoHibernate.cleanUsersTable();
//        userDaoHibernate.dropUsersTable();
    }
}
