package ru.house.manager.serviceDB;
import ru.house.manager.BLDB.Util;
import ru.house.manager.daoDB.ManagerDao;
import ru.house.manager.EntityDB.Managers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.sql.*;

public class ManagersService extends Util implements ManagerDao{

    Connection connection = getConnection();

    @Override
    public void add(Managers Manager) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO ManageCompanies_HMS (company_name, first_name, last_name, father_name, email, telephone, some_info, account_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setString(1,Manager.getCompanyName());
            preparedStatement.setString(2,Manager.getFirstName());
            preparedStatement.setString(3, Manager.getLastName());
            preparedStatement.setString(4, Manager.getFatherName());
            preparedStatement.setString(5, Manager.getEmail());
            preparedStatement.setString(6, Manager.getPhoneNumber());
            preparedStatement.setString(7, Manager.getSomeInfo());
            preparedStatement.setInt(8, Manager.getAccountId());

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
    public Managers getById(int id) throws SQLException {

        String sql = "SELECT company_name, FIRST_NAME, LAST_NAME, email, telephone FROM ManageCompanies_HMS WHERE account_id = ?";
        PreparedStatement preparedStatement = null;
        Managers manager = new Managers();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            manager.setCompanyName(resultSet.getString("COMPANY_NAME"));
            manager.setFirstName(resultSet.getString("FIRST_NAME"));
            manager.setLastName(resultSet.getString("LAST_NAME"));
            manager.setPhoneNumber(resultSet.getString("TELEPHONE"));
            manager.setEmail(resultSet.getString("EMAIL"));

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
        return manager;
    }

    @Override
    public Managers getByAccountId(int id) throws SQLException {

        String sql = "SELECT company_name, FIRST_NAME, LAST_NAME, manage_company_id, email, telephone FROM ManageCompanies_HMS WHERE account_id = ?";
        PreparedStatement preparedStatement = null;
        Managers manager = new Managers();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            manager.setCompanyName(resultSet.getString("COMPANY_NAME"));
            manager.setFirstName(resultSet.getString("FIRST_NAME"));
            manager.setLastName(resultSet.getString("LAST_NAME"));
            manager.setId(resultSet.getInt("manage_company_id"));
            manager.setPhoneNumber(resultSet.getString("TELEPHONE"));
            manager.setEmail(resultSet.getString("EMAIL"));

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
        return manager;
    }
}
