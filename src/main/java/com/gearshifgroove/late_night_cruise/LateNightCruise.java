package com.gearshifgroove.late_night_cruise;

import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import com.sun.tools.javac.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LateNightCruise extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setTitle("Late Night Cruise");
        mainStage.setScene(new MainMenuScene());
        mainStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}