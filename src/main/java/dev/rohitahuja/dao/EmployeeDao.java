package dev.rohitahuja.dao;

import dev.rohitahuja.DbConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {

    private static final Logger _log = LoggerFactory.getLogger(EmployeeDao.class);
    private static final String EMPLOYEE_INSERT = "insert into employee(employee_name, email) values (?, ?) returning id";

    public long insert(String employeeName, String email) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEE_INSERT)) {
            preparedStatement.setString(1, employeeName);
            preparedStatement.setString(2, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    long id = resultSet.getLong("id");
                    _log.info("Employee inserted with id: {}", id);
                    return id;
                }
                throw new SQLException("Insert succeeded but no id was returned");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
