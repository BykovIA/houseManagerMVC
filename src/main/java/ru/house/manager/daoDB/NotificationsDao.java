package ru.house.manager.daoDB;

import ru.house.manager.EntityDB.Notifications;

import java.sql.SQLException;
import java.util.List;

public interface NotificationsDao {
    void add (Notifications notification) throws SQLException;
    List<Notifications> getAllFromHouseId(int id) throws SQLException;
    List<Notifications> getAllFromManageId(int id) throws SQLException;
}
