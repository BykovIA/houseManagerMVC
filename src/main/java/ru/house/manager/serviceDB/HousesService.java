package ru.house.manager.serviceDB;
import ru.house.manager.BLDB.Util;
import ru.house.manager.daoDB.HousesDao;
import ru.house.manager.EntityDB.Houses;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.Random;

public class HousesService extends Util implements HousesDao{

    Connection connection = getConnection();

    @Override
    public void add(Houses House) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO HOUSES_HMS (manage_company_id, adress, city_name, amount_of_residents, access_token) VALUES(?, ?, ?, ?, ?)";
        Random rnd = new Random();
        int n = 10000 + rnd.nextInt(90000);

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1,House.getManageCompanyId());
            preparedStatement.setString(2,House.getAdress());
            preparedStatement.setString(3, House.getCity());
            preparedStatement.setInt(4, House.getResidentsNumber());
            preparedStatement.setInt(5, n);

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
    public Houses getById(int id) throws SQLException {

        String sql = "SELECT manage_company_id, adress, city_name, amount_of_residents, access_token FROM houses_HMS WHERE house_ID = ?";
        PreparedStatement preparedStatement = null;
        Houses house = new Houses();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            house.setManageCompanyId(resultSet.getInt("manage_company_id"));
            house.setAccessToken(resultSet.getInt("access_token"));
            house.setAdress(resultSet.getString("adress"));
            house.setResidentsNumber(resultSet.getInt("AMOUNT_OF_RESIDENTS"));
            house.setCity(resultSet.getString("city_name"));

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
        return house;
    }

    @Override
    public Houses getIdByToken(int token) throws SQLException {
        String sql = "SELECT HOUSE_ID, MANAGE_COMPANY_ID, ADRESS, CITY_NAME, AMOUNT_OF_RESIDENTS FROM HOUSES_HMS where ACCESS_TOKEN = ? AND ROWNUM = 1";
        PreparedStatement preparedStatement = null;
        Houses house = new Houses();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, token);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next() ) {
                System.out.println("no data");
            } else {

            house.setHouseId(resultSet.getInt("HOUSE_ID"));
            house.setManageCompanyId(resultSet.getInt("MANAGE_COMPANY_ID"));
            house.setAdress(resultSet.getString("ADRESS"));
            house.setCity(resultSet.getString("CITY_NAME"));
            house.setResidentsNumber(resultSet.getInt("AMOUNT_OF_RESIDENTS"));

            preparedStatement.executeUpdate();
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
        System.out.println(house.getHouseId());
        return house;
    }
}
