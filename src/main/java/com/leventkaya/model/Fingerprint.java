package com.leventkaya.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Fingerprint {
    private Long id;
    private String songName;
    private String fingerprintHash;
    private int timeStamp;
}
