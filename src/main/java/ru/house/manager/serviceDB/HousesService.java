package ru.house.manager.serviceDB;
import ru.house.manager.BLDB.Util;
import ru.house.manager.EntityDB.Accounts;
import ru.house.manager.EntityDB.Applications;
import ru.house.manager.daoDB.HousesDao;
import ru.house.manager.EntityDB.Houses;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HousesService extends Util implements HousesDao{

    Connection connection = getConnection();

    @Override
    public void add(Houses House) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO HOUSES_HMS (manage_company_id, adress, city_name, amount_of_residents, access_token) VALUES(?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1,House.getManageCompanyId());
            preparedStatement.setString(2,House.getAdress());
            preparedStatement.setString(3, House.getCity());
            preparedStatement.setInt(4, House.getResidentsNumber());
            preparedStatement.setInt(5, House.getAccessToken());

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
    public void update(Houses house) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE HOUSES_HMS SET CITY_NAME = ?, ADRESS = ?, ACCESS_TOKEN = ?, AMOUNT_OF_RESIDENTS = ? WHERE HOUSE_ID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,house.getCity());
            preparedStatement.setString(2, house.getAdress());
            preparedStatement.setInt(3, house.getAccessToken());
            preparedStatement.setInt(4, house.getResidentsNumber());
            preparedStatement.setInt(5, house.getHouseId());

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
            house.setHouseId(id);

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
    @Override
    public Houses getByManagerId(int id) throws SQLException {

        String sql = "SELECT adress, city_name, amount_of_residents, access_token FROM houses_HMS WHERE manage_company_id = ?";
        PreparedStatement preparedStatement = null;
        Houses house = new Houses();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

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
    public int houseCount(String address) throws SQLException {
        PreparedStatement preparedStatement = null;
        int tmp = -1;
        String sql = "select count(adress) from houses_hms where adress = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            tmp = resultSet.getInt("count(adress)");
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
        return tmp;
    }

    @Override
    public int tokenCount(int token) throws SQLException {
        PreparedStatement preparedStatement = null;
        int tmp = -1;
        String sql = "select count(access_token) from houses_hms where access_token = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, token);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            tmp = resultSet.getInt("count(access_token)");
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
        return tmp;
    }

    @Override
    public List<Houses> getAllHousesFromManagerId (int manageId) throws SQLException {
        List<Houses> housesList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT house_id, adress, city_name, amount_of_residents, access_token FROM houses_HMS WHERE manage_company_id = ?";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setInt(1, manageId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Houses house = new Houses();
                house.setHouseId(resultSet.getInt("house_id"));
                house.setAdress(resultSet.getString("adress"));
                house.setCity(resultSet.getString("city_name"));
                house.setResidentsNumber(resultSet.getInt("amount_of_residents"));
                house.setAccessToken(resultSet.getInt("access_token"));
                housesList.add(house);
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
        return housesList;
    }
}
