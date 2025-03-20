package com.leventkaya;

import com.leventkaya.db.SQLiteJDBCService;

import static com.leventkaya.util.AudionUtil.DATABASE_URL;

public class Main {

    public static void main(String[] args) {
        SQLiteJDBCService sqlService = new SQLiteJDBCService(DATABASE_URL);

        sqlService.connect();
    }
}