package com.leventkaya;

import com.leventkaya.db.SQLiteJDBCService;
import com.leventkaya.gui.AudionGUI;

import static com.leventkaya.util.AudionUtil.DATABASE_URL;
import static com.leventkaya.util.AudionUtil.SQL_FINGERPRINT_CREATE_STATEMENT;

public class Main {

    public static void main(String[] args) {
        SQLiteJDBCService sqlService = new SQLiteJDBCService(DATABASE_URL);
        sqlService.createTable(SQL_FINGERPRINT_CREATE_STATEMENT);

        AudionGUI app = new AudionGUI();
        app.run();
    }
}