package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {
      
    }

    public void createUsersTable() {
        String sql = "create table if not exists users(id serial primary key, name varchar(26),lastName varchar(25), age int )";
        try (Connection connection = Util.getconnection();
            Statement statement=connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dro = "drop table if exists users";
        try (Connection connection = Util.getconnection();
             Statement statement = connection.createStatement()) {
            statement.execute(dro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save="insert into users(name,lastName,age) values(?,?,?)";
        try (Connection connection = Util.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(save)) {
           preparedStatement.setString(1,name);
           preparedStatement.setString(2,lastName);
           preparedStatement.setByte(3,age);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove="delete from users where id=?";
        try (Connection connection = Util.getconnection();
             PreparedStatement preparedStatement = connection.prepareStatement(remove)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList=new ArrayList<>();
        String query="select * from users";
        try(Connection connection=Util.getconnection();
        Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query)){
            while(resultSet.next()){
                User user= new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
              userList.add(user);
            }
        }catch(SQLException e ){
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String clean = "delete  from users ";
        try (Connection connection = Util.getconnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(clean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}