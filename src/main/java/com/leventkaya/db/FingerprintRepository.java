package com.leventkaya.db;

import com.leventkaya.entity.Fingerprint;
import com.leventkaya.util.SqlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FingerprintRepository extends AbstractSQLService {

    public FingerprintRepository(String databaseUrl) {
        super(databaseUrl);
        createTable(SqlUtil.FINGERPRINT_CREATE_STATEMENT);
    }

    public void insertFingerprint(String songName, String hash, int timestamp) {
        insert(SqlUtil.FINGERPRINT_INSERT_STATEMENT, songName, hash, timestamp);
    }

    public List<Fingerprint> findAllFingerprints() {
        List<Fingerprint> fingerprints = new ArrayList<>();
        List<Object[]> resultSet = selectAll(SqlUtil.FINGERPRINT_FIND_ALL_STATEMENT, 4);

        for (Object[] row : resultSet) {
            Fingerprint fingerprint = Fingerprint.builder()
                    .id(((Number) row[0]).longValue()) // Fix: Convert to Long safely
                    .songName((String) row[1])
                    .fingerprintHash((String) row[2])
                    .timeStamp((int) row[3])
                    .build();
            fingerprints.add(fingerprint);
        }

        return fingerprints;
    }

}
