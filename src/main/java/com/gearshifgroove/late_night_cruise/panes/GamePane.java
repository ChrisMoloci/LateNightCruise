package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.Player;
import com.gearshifgroove.late_night_cruise.Tile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GamePane extends StackPane {
    private Image terrainImage;
    private Image carImage;
    private Tile terrainTile1;
    private Tile terrainTile2;
    private Player car;

    public GamePane() {
        terrainImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/track.png"));
        carImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/car.png"));
        terrainTile1 = new Tile(0, 0, 0, 3, terrainImage);
        terrainTile2 = new Tile(0, 1080, 0, 3, terrainImage);
        car = new Player(910, 490, carImage);

        Canvas canvas = new Canvas(1920, 1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> update(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        this.getChildren().add(canvas);
    }

    public void update(GraphicsContext gc) {
        gc.clearRect(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        terrainTile1.setyCoord(terrainTile1.getyCoord() + terrainTile1.getyCordSpeed());
        terrainTile2.setyCoord(terrainTile2.getyCoord() + terrainTile2.getyCordSpeed());
//
        if (terrainTile1.getyCoord() > 1080) {
            terrainTile1.setyCoord(-1080);
        }
        if (terrainTile2.getyCoord() > 1080) {
            terrainTile2.setyCoord(-1080);
        }

        gc.drawImage(terrainTile1.getImage(), terrainTile1.getxCoord(), terrainTile1.getyCoord());
        gc.drawImage(terrainTile2.getImage(), terrainTile2.getxCoord(), terrainTile2.getyCoord());

        gc.drawImage(car.getImage(), car.getxCoord(), car.getyCoord());

    }

}
