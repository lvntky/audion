package com.leventkaya.gui;

import static com.leventkaya.util.AudionUtil.DEFAULT_HEIGHT;
import static com.leventkaya.util.AudionUtil.DEFAULT_WIDTH;
import static com.leventkaya.util.AudionUtil.THEME_PATH;

import com.leventkaya.model.Audio;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AudionGUI extends Application {

  private static volatile AtomicBoolean isExit = new AtomicBoolean(false);
  private Label statusLabel;
  private ImageView spectrogramView;

  @Override
  public void start(Stage stage) throws Exception {
    log.info("GUI started");

    stage.setTitle("Audion - Audio Fingerprinting");
    StackPane layout = new StackPane();

    Scene scene = new Scene(layout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    scene.getStylesheets().add(
        (Objects.requireNonNull(getClass().getResource(THEME_PATH))).toExternalForm());

       /* Button startButton = new Button("Start Analysis");
        startButton.setFont(new Font("Arial", 16));
        startButton.setStyle(
                " -fx-text-fill: white; -fx-padding: 10 20;");
        startButton.setOnAction(e -> log.info("Start button clicked"));*/

    statusLabel = new Label("Drag & Drop an audio file here");
    spectrogramView = new ImageView();
    layout.getChildren().addAll(statusLabel, spectrogramView);

    scene.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ESCAPE) {
        log.info("ESC pressed, terminating GUI");
        isExit.set(true);
        Platform.exit();
      }
    });

    layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

    scene.setOnDragOver(event -> {
      if (event.getDragboard().hasFiles()) {
        event.acceptTransferModes(TransferMode.COPY);
      }
      event.consume();
    });

    scene.setOnDragDropped(this::handleFileDrop);

    stage.setScene(scene);
    stage.show();
  }

  private void handleFileDrop(DragEvent event) {
    Dragboard db = event.getDragboard();
    if (db.hasFiles()) {
      File audioFile = db.getFiles().get(0);
      log.info("Dropped file: {}", audioFile.getAbsolutePath());
      processAudio(audioFile);
    }
    event.setDropCompleted(true);
    event.consume();
  }

  private void processAudio(File audioFile) {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
      AudioFormat format = audioInputStream.getFormat();
      double duration = audioInputStream.getFrameLength() / format.getFrameRate();
      Audio audio = new Audio(audioFile, duration, (int) format.getSampleRate());

      statusLabel.setText("Processing: " + audioFile.getName());
      byte[] audioBytes = new byte[(int) (audioInputStream.getFrameLength()
          * format.getFrameSize())];

      audioInputStream.read(audioBytes, 0, audioBytes.length);

      // compute FFT for each frame

    } catch (UnsupportedAudioFileException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void run() {
    launch();
  }

  public static boolean hasExited() {
    return isExit.get();
  }
}
