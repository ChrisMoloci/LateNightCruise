package com.gearshifgroove.late_night_cruise;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LateNightCruise extends Application {
    private Terrain terrainTile1 = new Terrain(0, 0, 3);
    private Terrain terrainTile2 = new Terrain(0, 1080, 3);

    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(1920, 1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> update(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 1920, 1080);
        stage.setTitle("Late Night Cruise");
        stage.setScene(scene);
        stage.show();

    }
    public void update(GraphicsContext gc) {
        gc.clearRect(0, 0, 1920, 1080);

        terrainTile1.setyCoord(terrainTile1.getyCoord() + 3);
        terrainTile2.setyCoord(terrainTile2.getyCoord() + 3);

        if (terrainTile1.getyCoord() > 1080) {
            terrainTile1.setyCoord(-1080);
        }
        if (terrainTile2.getyCoord() > 1080) {
            terrainTile2.setyCoord(-1080);
        }

        gc.setFill(Color.GREEN);
        gc.fillRect(terrainTile1.getxCoord(), terrainTile1.getyCoord(), 1920, 1100);
        gc.setFill(Color.BLUE);
        gc.fillRect(terrainTile2.getxCoord(), terrainTile2.getyCoord(), 1920, 1100);
    }

    public static void main(String[] args) {
        launch();
    }
}