package com.leventkaya.db;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Slf4j
public class SQLiteJDBCService {

    private final String databaseUrl;
    private static SQLiteJDBCService instance;
    private Connection connection;

    public SQLiteJDBCService(String url) {
        this.databaseUrl = url;
        this.connection = null;

        try {
            connection = DriverManager.getConnection(databaseUrl);
            log.info("Connected to database");
        } catch (Exception e) {
            log.error("Failed to connect to database");
            throw new RuntimeException("Database could not be created: " + e.getMessage());
        }
    }

    public synchronized SQLiteJDBCService getInstance() {
        if (instance == null) {
            instance = new SQLiteJDBCService(databaseUrl);
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTable(String createStatement) {
        try(Statement statement = this.connection.createStatement()) {
            if (statement.execute(createStatement)) {
                log.info("Table created");
            } else {
                log.error("Table not created");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create table: " + createStatement);
        }
    }
}
