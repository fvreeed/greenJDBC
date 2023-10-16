package com.green.greenJDBC.dao;

import com.green.greenJDBC.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private static final String url = "jdbc:postgresql://localhost:5432/Users";
    private static final String username = "postgres";
    private static final String password = "postgres";

    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User create(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO  greenUser VALUES(?, ?, ?, ?, ?)");

        statement.setInt(1, user.getId());
        statement.setDouble(2, user.getDistance());
        statement.setDate(3, user.getDate());
        statement.setString(4, user.getName());
        statement.setString(5, user.getText());

        statement.executeUpdate();

        return user;
    }

    public List<User> read(int limit, int offset) throws SQLException {
        List<User> users = new ArrayList<>();

        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM greenUser";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            User user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setDistance(resultSet.getDouble("distance"));
            user.setDate(resultSet.getDate("run_date"));
            user.setName(resultSet.getString("user_name"));
            user.setText(resultSet.getString("user_comment"));

            users.add(user);
        }
        System.out.println(users);
        return users;
    }

    public User update(int id, User user) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE greenUser SET distance=?," +
                        "run_date=?, user_name=?, user_comment=? WHERE user_id=?");

        preparedStatement.setDouble(1, user.getDistance());
        preparedStatement.setDate(2, user.getDate());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getText());
        preparedStatement.setInt(5, id);

        preparedStatement.executeUpdate();

        return user;
    }

    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM greenUser WHERE user_id=?");

        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    public User findById(int id) throws SQLException {
        User user = new User();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM greenUser WHERE user_id=?");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        user.setId(resultSet.getInt("user_id"));
        user.setDistance(resultSet.getDouble("distance"));
        user.setDate(resultSet.getDate("run_date"));
        user.setName(resultSet.getString("user_name"));
        user.setText(resultSet.getString("user_comment"));

        return user;
    }
}