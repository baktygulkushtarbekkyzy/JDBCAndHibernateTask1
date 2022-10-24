package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.Util;

import javax.management.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao, AutoCloseable {
    SessionFactory sessionFactory = Util.creatSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createNativeQuery("" +
                "create table if not exists users(" +
                "id serial primary key, " +
                "name varchar(26)," +
                "lastName varchar(25), " +
                "age int)").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory1=Util.creatSessionFactory();
        Session session=sessionFactory1.openSession();
        session.beginTransaction();


        session.createNativeQuery("drop table users").executeUpdate();


        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.creatSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.persist(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.creatSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.createQuery("select  c from User c where c.id= :id",
                User.class).setParameter("id", id).getSingleResult();
        session.remove(user);
//        User user = session.get(User.class, id);
//        session.remove(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory1 = Util.creatSessionFactory();
        Session session = sessionFactory1.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("select c from  User c ", User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory1 = Util.creatSessionFactory();
        Session session = sessionFactory1.openSession();
        session.beginTransaction();
        session.createQuery("delete  from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void close() throws Exception {

    }
}
