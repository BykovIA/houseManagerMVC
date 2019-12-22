package ru.house.manager.daoDB;

import ru.house.manager.EntityDB.Comments;

import java.sql.SQLException;
import java.util.List;

public interface CommentsDao {
    void add(Comments comment) throws SQLException;
    List<Comments> getAllFromApplicationId(int id) throws SQLException;
}
