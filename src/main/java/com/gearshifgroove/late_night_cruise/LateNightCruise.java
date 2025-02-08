package com.gearshifgroove.late_night_cruise;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LateNightCruise extends Application {
    private Image terrainImage = new Image(getClass().getResourceAsStream("track.png"));
    private Image carImage = new Image(getClass().getResourceAsStream("car.png"));
    private Tile terrainTile1 = new Tile(0, 0, 0, 3, terrainImage);
    private Tile terrainTile2 = new Tile(0, 1080, 0, 3, terrainImage);
    private Player car = new Player(910, 490, carImage);

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

        terrainTile1.setyCoord(terrainTile1.getyCoord() + terrainTile1.getyCordSpeed());
        terrainTile2.setyCoord(terrainTile2.getyCoord() + terrainTile2.getyCordSpeed());

        if (terrainTile1.getyCoord() > 1080) {
            terrainTile1.setyCoord(-1080);
        }
        if (terrainTile2.getyCoord() > 1080) {
            terrainTile2.setyCoord(-1080);
        }

        gc.setFill(Color.GREEN);
        gc.drawImage(terrainTile1.getImage(), terrainTile1.getxCoord(), terrainTile1.getyCoord());
        gc.setFill(Color.BLUE);
        gc.drawImage(terrainTile2.getImage(), terrainTile2.getxCoord(), terrainTile2.getyCoord());
//
//        gc.setFill(Color.RED);
//        gc.fillRect(car.getxCoord(), car.getyCoord(), 100, 100);

//        gc.drawImage(new Image(getClass().getResourceAsStream("car.png")), 100, 100, 100, 100);
        gc.drawImage(car.getImage(), car.getxCoord(), car.getyCoord());

    }

    public static void main(String[] args) {
        launch();
    }
}