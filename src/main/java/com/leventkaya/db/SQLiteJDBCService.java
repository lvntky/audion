package com.leventkaya.db;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static com.leventkaya.util.AudionUtil.DATABASE_URL;

@Slf4j
@Getter
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

    public synchronized static SQLiteJDBCService getInstance() {
        if (instance == null) {
            instance = new SQLiteJDBCService(DATABASE_URL);
        }
        return instance;
    }

    public void createTable(String createStatement) {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(createStatement);
            log.info("Table creation statement executed successfully.");
        } catch (Exception e) {
            log.error("Failed to create table: " + e.getMessage());
            throw new RuntimeException("Failed to create table: " + createStatement, e);
        }
    }


    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
                log.info("Connection closed");
            } else {
                log.error("Connection not closed, this connection is null");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to close connection: " + e.getMessage());
        }
    }
}
