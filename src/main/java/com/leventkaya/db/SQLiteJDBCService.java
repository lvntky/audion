package com.leventkaya.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
@RequiredArgsConstructor
public class SQLiteJDBCService {
    private final String databaseUrl;

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseUrl);
            log.info("Connected to database");
        } catch (Exception e) {
            log.error("Failed to connect to database: {}", e.getMessage());
        }
        return connection;
    }

}
