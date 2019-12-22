package ru.house.manager.serviceDB;

import ru.house.manager.BLDB.Util;
import ru.house.manager.EntityDB.Comments;
import ru.house.manager.daoDB.CommentsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentsService extends Util implements CommentsDao {

    Connection connection = getConnection();

    @Override
    public void add(Comments comment) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO COMMENTS_HMS (APPLICATION_ID, COMMENTATOR, TEXT, COMMENT_DATE) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, comment.getApplicationId());
            preparedStatement.setString(2, comment.getCommentator());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.setString(4, comment.getDate());
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
    public List<Comments> getAllFromApplicationId (int id) throws SQLException {
        List<Comments> commentsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT COMMENT_ID, COMMENTATOR, TEXT, COMMENT_DATE FROM COMMENTS_HMS WHERE APPLICATION_ID = ? order by COMMENT_ID ASC";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comments comment = new Comments();
                comment.setCommentId(resultSet.getInt("COMMENT_ID"));
                comment.setCommentator(resultSet.getString("COMMENTATOR"));
                comment.setText(resultSet.getString("TEXT"));
                comment.setDate(resultSet.getString("COMMENT_DATE"));
                comment.setApplicationId(id);
                commentsList.add(comment);
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
        return commentsList;
    }
}
