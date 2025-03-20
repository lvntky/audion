package com.leventkaya.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AudionUtil {
    public static final String DATABASE_URL = "jdbc:sqlite:audion.db";
    public static final String THEME_PATH = "/css/dracula.css";
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;
    public static final String SQL_FINGERPRINT_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS fingerprint (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "song_name TEXT NOT NULL, " +
            "fingerprint_hash TEXT NOT NULL UNIQUE, " +
            "timestamp INTEGER NOT NULL);";
}
