package ru.house.manager.daoDB;
import ru.house.manager.EntityDB.Accounts;
import java.sql.SQLException;
import java.util.List;

public interface AccountsDao {
    // create
    void add(Accounts Account) throws SQLException;

    // read
    List<Accounts> getAll() throws SQLException;
    Accounts getById(int id) throws SQLException;
    Accounts getByEmail(String eMail) throws SQLException;

    // update
    void update(Accounts Account) throws SQLException;

    // delete
    void remove(Accounts Account) throws SQLException;

    int emailCount(String eMail) throws SQLException;
}
