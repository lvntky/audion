package com.leventkaya;

import com.leventkaya.db.FingerprintRepository;
import com.leventkaya.gui.AudionGUI;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static com.leventkaya.util.AudionUtil.DATABASE_URL;

@Slf4j
public class Main {

    public static void main(String[] args) {

        FingerprintRepository fingerprintRepository = new FingerprintRepository(DATABASE_URL);
        fingerprintRepository.insertFingerprint("a", UUID.randomUUID().toString(), 123456);

        log.info("All fingerprints found : {}", fingerprintRepository.findAllFingerprints());

        AudionGUI app = new AudionGUI();
        app.run();
    }
}