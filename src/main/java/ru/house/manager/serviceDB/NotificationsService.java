package ru.house.manager.serviceDB;

import ru.house.manager.BLDB.Util;
import ru.house.manager.EntityDB.Notifications;
import ru.house.manager.daoDB.NotificationsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationsService extends Util implements NotificationsDao {

    Connection connection = getConnection();

    @Override
    public void add(Notifications notification) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO NOTIFICATIONS_HMS (HOUSE_ID, MANAFER_ID, USER_VISIBILITY, NOTIF_DATE, TEXT, STATUS) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, notification.getHouseId());
            preparedStatement.setInt(2, notification.getManagerId());
            preparedStatement.setInt(3, notification.getUserVisibility());
            preparedStatement.setString(4, notification.getDate());
            preparedStatement.setString(5, notification.getText());
            preparedStatement.setString(6, notification.getStatus());
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
    public List<Notifications> getAllFromHouseId(int id) throws SQLException {
        List<Notifications> notificationsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT NOTIFICATION_ID, MANAGER_ID, USER_VISIBILITY, NOTIF_DATE, TEXT, STATUS FROM NOTIFICATIONS_HMS WHERE HOUSE_ID = ? order by NOTIF_DATE DESC";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Notifications notification = new Notifications();
                notification.setNotificationId(resultSet.getInt("NOTIFICATION_ID"));
                notification.setManagerId(resultSet.getInt("MANAGER_ID"));
                notification.setUserVisibility(resultSet.getInt("USER_VISIBILITY"));
                notification.setHouseId(id);
                notification.setDate(resultSet.getString("NOTIF_DATE"));
                notification.setText(resultSet.getString("TEXT"));
                notification.setStatus(resultSet.getString("STATUS"));
                notificationsList.add(notification);
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
        return notificationsList;
    }

    @Override
    public List<Notifications> getAllFromManageId(int id) throws SQLException {
        List<Notifications> notificationsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT NOTIFICATION_ID, HOUSE_ID, USER_VISIBILITY, NOTIF_DATE, TEXT, STATUS FROM NOTIFICATIONS_HMS WHERE MANAGER_ID = ? order by NOTIF_DATE DESC";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Notifications notification = new Notifications();
                notification.setNotificationId(resultSet.getInt("NOTIFICATION_ID"));
                notification.setHouseId(resultSet.getInt("HOUSE_ID"));
                notification.setUserVisibility(resultSet.getInt("USER_VISIBILITY"));
                notification.setManagerId(id);
                notification.setDate(resultSet.getString("NOTIF_DATE"));
                notification.setText(resultSet.getString("TEXT"));
                notification.setStatus(resultSet.getString("STATUS"));
                notificationsList.add(notification);
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
        return notificationsList;
    }

}
