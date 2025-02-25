package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.Player;
import com.gearshifgroove.late_night_cruise.Tile;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class GamePane extends StackPane {
    private Image terrainImage;
    private Image carImage;
    private Tile terrainTile1;
    private Tile terrainTile2;
    private Player car;
    private boolean keyPressed = false;
    private char direction = 'N';

    public GamePane() {
        System.out.println("Game Started");
        terrainImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/track.png"));
        carImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/car.png"));
        terrainTile1 = new Tile(0, 0, 0, 3, terrainImage);
        terrainTile2 = new Tile(0, 1080, 0, 3, terrainImage);
        car = new Player(883, 490, carImage);

        Media media = new Media(new File(getClass().getResource("/com/gearshifgroove/late_night_cruise/Songs/0002.wav").toExternalForm()).toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();

        // Debug why button must be present for keyboard input to work
        Button button = new Button("Start");
        button.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
        });

        this.getChildren().add(button);

        Canvas canvas = new Canvas(1920, 1080);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.requestFocus();

        this.setOnKeyPressed(event -> {
//            System.out.println(event.getCode());
//            System.out.println("Key pressed");

            switch (event.getCode()) {
//                case W:
//                    System.out.println("up");
//                    break;
                case A:
                    System.out.println("left");
                    direction = 'L';
                    break;
//                case S:
//                    System.out.println("down");
//                    break;
                case D:
                    System.out.println("right");
                    direction = 'R';
                    break;
            }
        });

//        this.setOnKeyReleased(event -> {
//            switch (event.getCode()) {
//                case W:
//                    System.out.println("up release");
//                    break;
//                case A:
//                    System.out.println("left release");
//                    direction = 'N';
//                    break;
//                case S:
//                    System.out.println("down release");
//                    break;
//                case D:
//                    System.out.println("right release");
//                    direction = 'N';
//                    break;
//            }
//        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> update(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        this.getChildren().add(canvas);
    }

    public void update(GraphicsContext gc) {
        gc.clearRect(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        terrainTile1.setyCoord(terrainTile1.getyCoord() + terrainTile1.getyCordSpeed());
        terrainTile2.setyCoord(terrainTile2.getyCoord() + terrainTile2.getyCordSpeed());

        if (direction != 'N') {
            if (direction == 'L') {
                car.moveLeft();
            }
            if (direction == 'R') {
                car.moveRight();
            }
            direction = 'N';
        }

        car.setxCoord(car.getxCoord() + car.getxCordSpeed());

        if (terrainTile1.getyCoord() > 1080) {
            terrainTile1.setyCoord(-1080);
        }
        if (terrainTile2.getyCoord() > 1080) {
            terrainTile2.setyCoord(-1080);
        }

//        this.setOnKeyPressed(event -> {
//            System.out.println(event.getCode());
//        });

        gc.drawImage(terrainTile1.getImage(), terrainTile1.getxCoord(), terrainTile1.getyCoord());
        gc.drawImage(terrainTile2.getImage(), terrainTile2.getxCoord(), terrainTile2.getyCoord());

        gc.drawImage(car.getImage(), car.getxCoord(), car.getyCoord());

    }

}
