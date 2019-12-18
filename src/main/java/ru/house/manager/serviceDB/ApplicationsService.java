package ru.house.manager.serviceDB;
import ru.house.manager.BLDB.Util;
import ru.house.manager.EntityDB.Accounts;
import ru.house.manager.daoDB.ApplicationsDao;
import ru.house.manager.EntityDB.Applications;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationsService extends Util implements ApplicationsDao {

    Connection connection = getConnection();

    @Override
    public void add(Applications Application) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO APPLICATIONS_HMS (MANAGE_COMPANY_ID, RESIDENT_ID, DATA_TIME, TEXT, STATUS, IMAGES_NAME, HOUSE_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1,Application.getManageId());
            preparedStatement.setInt(2, Application.getUserId());
            preparedStatement.setString(3, Application.getData());
            preparedStatement.setString(4, Application.getText());
            preparedStatement.setString(5, Application.getStatus());
            preparedStatement.setString(6, Application.getImageName());
            preparedStatement.setInt(7,Application.getHouse_id());

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
    public void update(Applications Application) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE APPLICATIONS_HMS SET STATUS = ? WHERE APPLICATION_ID = ?";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setString(1, Application.getStatus());
            preparedStatement.setInt(2,Application.getApplicationsId());

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
    public List<Applications> getAllForResident(int id, String status) throws SQLException {
        List<Applications> applicationsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT APPLICATION_ID, MANAGE_COMPANY_ID, DATA_TIME, TEXT, IMAGES_NAME, HOUSE_ID FROM APPLICATIONS_HMS WHERE RESIDENT_ID = ? AND STATUS = ?";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, status);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Applications application = new Applications();
                application.setApplicationsId(resultSet.getInt("APPLICATION_ID"));
                application.setManageId(resultSet.getInt("manage_company_ID"));
                application.setData(resultSet.getString("DATA_TIME"));
                application.setText(resultSet.getString("TEXT"));
                application.setImageName(resultSet.getString("IMAGES_NAME"));
                application.setHouse_id(resultSet.getInt("HOUSE_ID"));
                application.setUserId(id);
                application.setStatus(status);
                applicationsList.add(application);
            }
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
        return applicationsList;
    }



    @Override
    public List<Applications> getAllForManager(int id, String status) throws SQLException {
        List<Applications> applicationsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT APPLICATION_ID, RESIDENT_ID, DATA_TIME, TEXT, IMAGES_NAME, HOUSE_ID FROM APPLICATIONS_HMS WHERE MANAGE_COMPANY_ID = ? AND STATUS = ?";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, status);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Applications application = new Applications();
                application.setApplicationsId(resultSet.getInt("APPLICATION_ID"));
                application.setUserId(resultSet.getInt("RESIDENT_ID"));
                application.setData(resultSet.getString("DATA_TIME"));
                application.setText(resultSet.getString("TEXT"));
                application.setImageName(resultSet.getString("IMAGES_NAME"));
                application.setHouse_id(resultSet.getInt("HOUSE_ID"));
                application.setManageId(id);
                application.setStatus(status);
                applicationsList.add(application);
            }
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
        return applicationsList;
    }

    @Override
    public List<Applications> getAllForHouse(int id, String status) throws SQLException {
        List<Applications> applicationsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT APPLICATION_ID, RESIDENT_ID, DATA_TIME, TEXT, IMAGES_NAME, MANGER_COMPANY_ID FROM APPLICATIONS_HMS WHERE HOUSE_ID = ? AND STATUS = ?";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, status);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Applications application = new Applications();
                application.setApplicationsId(resultSet.getInt("APPLICATION_ID"));
                application.setUserId(resultSet.getInt("RESIDENT_ID"));
                application.setData(resultSet.getString("DATA_TIME"));
                application.setText(resultSet.getString("TEXT"));
                application.setImageName(resultSet.getString("IMAGES_NAME"));
                application.setManageId(resultSet.getInt("MANGER_COMPANY_ID"));
                application.setHouse_id(id);
                application.setStatus(status);
                applicationsList.add(application);
            }
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
        return applicationsList;
    }
}
