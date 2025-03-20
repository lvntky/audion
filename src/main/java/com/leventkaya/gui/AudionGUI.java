package com.leventkaya.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.leventkaya.util.AudionUtil.*;

@RequiredArgsConstructor
@Slf4j
public class AudionGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        log.info("GUI started");

        stage.setTitle("Audion - Audio Fingerprinting");
        StackPane layout = new StackPane();

        Scene scene = new Scene(layout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        /*scene.getStylesheets().add(
                (getClass().getResource(THEME_PATH)).toExternalForm());*/

        Button startButton = new Button("Start Analysis");
        startButton.setFont(new Font("Arial", 16));
        startButton.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10 20;");
        startButton.setOnAction(e -> log.info("Start button clicked"));

        layout.getChildren().addAll(startButton);

        stage.setScene(scene);
        stage.show();
    }

    public void run() {
        launch();
    }
}
