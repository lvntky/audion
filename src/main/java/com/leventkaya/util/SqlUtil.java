package com.leventkaya.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlUtil {

    public static final String FINGERPRINT_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS fingerprint (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "song_name TEXT NOT NULL, " +
            "fingerprint_hash TEXT NOT NULL UNIQUE, " +
            "timestamp INTEGER NOT NULL);";

    public static final String FINGERPRINT_INSERT_STATEMENT = "INSERT INTO fingerprint (song_name, fingerprint_hash, timestamp) VALUES (?, ?, ?);";

    public static final String FINGERPRINT_FIND_ALL_STATEMENT = "SELECT * FROM fingerprint;";
}
