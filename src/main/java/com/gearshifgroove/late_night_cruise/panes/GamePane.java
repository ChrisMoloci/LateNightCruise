package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.*;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePane extends StackPane {
    private Image terrainImage;
    private Image carImage;
    private Tile terrainTile1;
    private Tile terrainTile2;
    private Image coinImage;
    private Image fuelImage;
    private List<Coin> coins = new ArrayList<>();
    private List<Fuel> fuels = new ArrayList<>();
    private Random rand = new Random();
    private Player car;
    private boolean keyPressed = false;
    private char direction = 'N';

    public GamePane() {
        System.out.println("Game Started");
        terrainImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/track.png"));
        carImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/car.png"));
        coinImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/coin.png"));
        fuelImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/fuel.png"),50,50, true,true);
        terrainTile1 = new Tile(0, 0, 0, 3, terrainImage);
        terrainTile2 = new Tile(0, 1080, 0, 3, terrainImage);
        car = new Player(883, 490, carImage);






        for(int i =0; i<4;i++) {
            //// Create a random Coin object and add it to the list
            int randomX = rand.nextInt(); /// Random x coordinate (4 lanes)
            ///Random Y position
            int randomY = rand.nextInt(500);
            coins.add(new Coin(randomX, randomY, coinImage));

        }


        for(int i=0;i<4; i++) {
           int  randomX = rand.nextInt();
            int randomY = rand.nextInt(500);
            fuels.add(new Fuel(randomX, randomY, fuelImage));

        }



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



    /// This will make sure it return in a random lane (100,300,500,700)
    private int getRandomLane(){
        /// These are the lanes i want

        int minX = 800;
        int maxX = 1000;

        /// This will make sure x is always within the center range
        int  randomX = rand.nextInt(maxX -minX + 1) + minX;
        return randomX;
        ///int [] lanes = {100,300,500,700};
        ///return lanes[rand.nextInt(lanes.length)];
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


        List<Coin> collectedCoins = new ArrayList<>();
        List<Fuel> collectedFuels = new ArrayList<>();

        for (Coin coin : coins) {
            //// Moving downwards
            coin.setyCoord(coin.getyCoord() + 2);


            /// Check if coin is off the screen
            if (coin.getyCoord() > Const.WINDOW_HEIGHT) {
                /// Here reset to the top
                coin.setyCoord(-50);
                coin.setxCoord(getRandomLane());
            }

            /// Here were drawing the coin with a size of 30x30 pixels instead of its original size
            /// Resizing the coin to 30x30
            gc.drawImage(coin.getImage(), coin.getxCoord(), coin.getyCoord(),30,30);

            /// IF collision with the car
            if (Math.abs(car.getxCoord() - coin.getxCoord()) < 30 && Math.abs(car.getyCoord() - coin.getyCoord()) < 30) {
                /// This will increase coin count by 1
                car.setCoinCount(car.getCoinCount() + 1);
                /// Thsi will reset the coin position to the top
                coin.setyCoord(-50);
                /// In the random lane
                coin.setxCoord(getRandomLane());
            }
        }


        for (Fuel fuel : fuels) {
            /// Moves downwards
            fuel.setyCoord(fuel.getyCoord() + 2);


            /// Check if fuel is off the screen
            if (fuel.getyCoord() > Const.WINDOW_HEIGHT) {
                fuel.setyCoord(-50);
                fuel.setxCoord(getRandomLane());
            }

            /// same as the coins 30x30
            gc.drawImage(fuel.getImage(), fuel.getxCoord(), fuel.getyCoord(), 30, 30); // Resize to 30x30


            /// Check if the car is close to the fuel
            if (Math.abs(car.getxCoord() - fuel.getxCoord()) < 30 && Math.abs(car.getyCoord() - fuel.getyCoord()) < 30) {
                car.setGasLevel(car.getGasLevel() + 1);
                fuel.setyCoord(-50);
                fuel.setxCoord(getRandomLane());
            }
        }


        ///This will draw the coin and gas level boxes
        drawScoreBox(gc);

    }

        private void drawScoreBox(GraphicsContext gc){
            /// Raw Coin box
            gc.setFill(Color.BLACK);

            ///THis would be the box
            gc.fillRect(Const.WINDOW_WIDTH-150,300,120,50);
            gc.setFill(Color.WHITE);
            gc.fillText("Coins: " + car.getCoinCount(),Const.WINDOW_WIDTH -140,330);


            /// Gas Box
            gc.setFill(Color.BLACK);

            ///THis would be the box
            gc.fillRect(Const.WINDOW_WIDTH-150,200,120,50);
            gc.setFill(Color.WHITE);
            gc.fillText("Gas: " + car.getGasLevel(),Const.WINDOW_WIDTH -140,230);

        }




    }


