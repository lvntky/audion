package com.leventkaya.db;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractSQLService {

    private final String databaseUrl;
    protected Connection connection;

    public AbstractSQLService(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        try {
            this.connection = DriverManager.getConnection(databaseUrl);
            log.info("Connected to database.");
        } catch (SQLException e) {
            log.error("Failed to connect to database: {}", e.getMessage());
            throw new RuntimeException("Database connection error", e);
        }
    }

    public void createTable(String createStatement) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(createStatement);
            log.info("Table creation statement executed successfully.");
        } catch (SQLException e) {
            log.error("Failed to create table: {}", e.getMessage());
            throw new RuntimeException("Failed to create table: " + createStatement, e);
        }
    }

    public void insert(String insertStatement, Object... values) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertStatement)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
            log.info("Insert statement executed successfully.");
        } catch (SQLException e) {
            log.error("Failed to insert data: {}", e.getMessage());
            throw new RuntimeException("Failed to execute insert statement: " + insertStatement, e);
        }
    }

    public List<Object[]> selectAll(String query, int columnCount) {
        List<Object[]> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                results.add(row);
            }
            log.info("Select all statement executed successfully.");
        } catch (SQLException e) {
            log.error("Failed to execute select all statement: {}", e.getMessage());
            throw new RuntimeException("Failed to execute select all statement: " + query, e);
        }
        return results;
    }

    public Object[] select(String query, Object... values) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    Object[] row = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        row[i] = resultSet.getObject(i + 1);
                    }
                    log.info("Select statement executed successfully.");
                    return row;
                }
            }
        } catch (SQLException e) {
            log.error("Failed to execute select statement: {}", e.getMessage());
            throw new RuntimeException("Failed to execute select statement: " + query, e);
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                log.info("Connection closed.");
            } else {
                log.warn("Connection was already null.");
            }
        } catch (SQLException e) {
            log.error("Failed to close connection: {}", e.getMessage());
            throw new RuntimeException("Failed to close connection.", e);
        }
    }
}
