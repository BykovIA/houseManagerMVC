package ru.house.manager.serviceDB;
import ru.house.manager.BLDB.Util;
import ru.house.manager.daoDB.UsersDao;
import ru.house.manager.EntityDB.Users;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService extends Util implements UsersDao {

    Connection connection = getConnection();

    @Override
    public void add(Users testUser) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USERS_HMS (HOUSE_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, FATHER_NAME, PHONE_NUMBER, E_MAIL, ROOM_NUMBER) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1,testUser.getHouseId());
            preparedStatement.setInt(2,testUser.getAccount_id());
            preparedStatement.setString(3, testUser.getFirstName());
            preparedStatement.setString(4, testUser.getLastName());
            preparedStatement.setString(5, testUser.getFatherName());
            preparedStatement.setString(6, testUser.getPhoneNumber());
            preparedStatement.setString(7, testUser.getEmail());
            preparedStatement.setString(8, testUser.getRoomNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Users> getAll() throws SQLException {
        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT USER_ID, FIRST_NAME, LAST_NAME FROM USERS_HMS";
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("USER_ID"));
                users.setFirstName(resultSet.getString("FIRST_NAME"));
                users.setLastName(resultSet.getString("LAST_NAME"));
                usersList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return usersList;
    }

    @Override
    public Users getById(int id) throws SQLException {

        String sql = "SELECT FIRST_NAME, LAST_NAME, HOUSE_ID FROM USERS_HMS WHERE USER_ID = ?";
        PreparedStatement preparedStatement = null;
        Users users = new Users();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            users.setFirstName(resultSet.getString("FIRST_NAME"));
            users.setLastName(resultSet.getString("LAST_NAME"));
            users.setHouseId(resultSet.getInt("HOUSE_ID"));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return users;
    }

    @Override
    public void update(Users user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE USERS_HMS SET FIRST_NAME = ?, LAST_NAME = ? WHERE USER_ID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Users user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE  FROM USERS_HMS WHERE USER_ID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT USER_ID FROM USERS_HMS WHERE USER_ID = ( SELECT MAX(USER_ID) FROM USERS_HMS)";
        Statement statement = null;
        int id = -1;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            id = resultSet.getInt("USER_ID");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return id;

    }
}
