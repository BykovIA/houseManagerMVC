package ru.house.manager.daoDB;
import ru.house.manager.EntityDB.Houses;
import java.sql.SQLException;

public interface HousesDao {
    void add(Houses House) throws SQLException;
    Houses getById(int id) throws SQLException;
    Houses getIdByToken(int token) throws SQLException;
    Houses getByManagerId (int id) throws  SQLException;
}
