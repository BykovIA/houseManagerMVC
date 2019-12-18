package ru.house.manager.daoDB;
import ru.house.manager.EntityDB.Applications;
import java.sql.SQLException;
import java.util.List;

public interface ApplicationsDao {
    // create
    void add(Applications Application) throws SQLException;

    // read
    List<Applications> getAllForResident(int id, String status) throws SQLException;
    List<Applications> getAllForManager(int id, String status) throws SQLException;
    List<Applications> getAllForHouse(int id, String status) throws SQLException;

    // update
    void update(Applications Application) throws SQLException;

}
