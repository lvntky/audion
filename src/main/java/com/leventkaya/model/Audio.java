package com.leventkaya.model;

import java.io.File;
import lombok.Data;

@Data
public class Audio {
  private File file;
  private String filePath;
  private double duration;
  private int sampleRate;

  public Audio(File file, double duration, int sampleRate) {
    this.file = file;
    this.filePath = file.getAbsolutePath();
    this.duration = duration;
    this.sampleRate = sampleRate;
  }

  @Override
  public String toString() {
    return "Audio{" +
        "filePath='" + filePath + '\'' +
        ", duration=" + duration + " sec" +
        ", sampleRate=" + sampleRate + " Hz" +
        '}';
  }

}
