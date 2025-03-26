package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.*;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;



public class GamePane extends StackPane {
    public static AudioClip carMedia;
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

    /// so we can control it later
    private Timeline timeline;
    private GraphicsContext gc;

    private Text gameOverText;
    private VBox buttonsLayout;
    private boolean isGameOver = false;
    private boolean isPaused= false;
    private Button pauseButton;

    /// Creating gas level
    private double gasLevel = 99.9;
    public GamePane() {
        System.out.println("Game Started");
        terrainImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/track.png"));
        carImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/car.png"));
        coinImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/coin.png"));
        fuelImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/gas-can.png"));
        terrainTile1 = new Tile(0, 0, 0, 3, terrainImage);
        terrainTile2 = new Tile(0, 1080, 0, 3, terrainImage);
        car = new Player(883, 490, carImage);
        car.setCoinCount(ScoreSystem.getStoredScore());

        carMedia = new AudioClip(new File("src/main/resources/com/gearshifgroove/late_night_cruise/carSound.wav").toURI().toString());
        carMedia.setCycleCount(AudioClip.INDEFINITE);
        carMedia.setVolume(0.1);
        carMedia.play();

        // Debug why button must be present for keyboard input to work
        Button button = new Button("Start");
        button.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
        });

        this.getChildren().add(button);

        Canvas canvas = new Canvas(1920, 1080);
         gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.PAUSE || event.getCode() == KeyCode.ESCAPE){
                hitPause();
            }

            if (isPaused){
                isPaused = false;
            }

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

        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> update(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        this.getChildren().add(canvas);
    }

    /// This will generate a random lane for the coin or fuel
    private int getRandomLane(){
        /// Possible lanes
        int[] lanes = {800,900,982,1068};
        /// Randomly choose one lane
        return lanes[rand.nextInt(lanes.length)];
        //return rand.nextInt(maxX-minX + 1) + minX;
    }

    public void update(GraphicsContext gc) {
        if (isGameOver){
            showGameOverScreen(gc);
            return;
        }

        if (isPaused){
            showPausedMessage();
            return;
        }

        int key = rand.nextInt(100);

        if (key == 0) {
            key = rand.nextInt(0, 2);
            if (key == 0) {
                System.out.println("Coin");
            } else if (key == 1) {
                System.out.println("Fuel");
            } else {
                System.out.println("Unknown generator");
            }

        }

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
        /// Coin & Fuel
        /// Random number 1 and 1000
        int randNum = rand.nextInt(1000) +1;
        /// Random number between 1 and 2
        int randTime = rand.nextInt(2)+1;

            /// If both numbers match spawn a coin or fuel
        if (randNum >= 995 && randTime==2){
            /// random number between 1-2
            int spawnType = rand.nextInt(2)+1;
            int randomLane = getRandomLane();

            /// If it's 1
            if (spawnType==1){
                /// Here were just creating a new coin at the top,
                ///-50 is used so the coin is above the visible area
                Coin newCoin = new Coin(randomLane,-50,coinImage);
                /// Add to the coin list
                coins.add(newCoin);

                /// If it's 2
            } else if (spawnType==2) {
                Fuel newFuel = new Fuel(randomLane,-50,fuelImage);
                fuels.add(newFuel);
            }
        }

        List<Coin> collectedCoins = new ArrayList<>();
        for(Coin coin:  coins){
            /// Moving it downwards by 2 pixels per frame
            coin.setyCoord(coin.getyCoord()+2);

            /// Check if the coin has moved off the screen, if y is greater than 1080, which is the bottom
            if (coin.getyCoord()>1080){
                /// Reset y to -50
                coin.setyCoord(-50);
                /// Randomly place the coin in one of the four lanes
                coin.setxCoord(getRandomLane());
        }

            gc.drawImage(coin.getImage(),coin.getxCoord(),coin.getyCoord());
            /// Check for the Collision with the car.
            if (Math.abs(car.getxCoord() - coin.getxCoord())<30 && Math.abs(car.getyCoord()-coin.getyCoord())<30){
                /// Getting the points by 1
                ScoreSystem.updateStoredScore(ScoreSystem.getStoredScore() + 1);
                car.setCoinCount(ScoreSystem.getStoredScore());
//                car.setCoinCount(car.getCoinCount()+1);
                /// Here were removing it from the list
                coins.remove(coin);
                collectedCoins.add(coin);
            }
        }

        List<Fuel> collectedFuels = new ArrayList<>();
        for (Fuel fuel: fuels){
            fuel.setyCoord(fuel.getyCoord() + 2);

            /// Check if the fuel reaches bottom or collected
            if (fuel.getyCoord()>1080){
                fuel.setyCoord(-50);
                fuel.setxCoord(getRandomLane());
            }
            gc.drawImage(fuel.getImage(),fuel.getxCoord(), fuel.getyCoord());

            if (Math.abs(car.getxCoord() - fuel.getxCoord())<30 && Math.abs(car.getyCoord() - fuel.getyCoord())<30){
                car.setGasCount(car.getGasCount()+1);
                fuels.remove(fuel);
                collectedFuels.add(fuel);

                gasLevel += 5;
                if (gasLevel > 100){
                    gasLevel =100;
                }

            }

        }

        if (gasLevel >0){
            gasLevel -=0.03;
        }else{
            gasLevel =0;
            isGameOver = true;
        }

        /// Round the gas level
        double roundedGasLevel = Math.round(gasLevel * 100.0) /100.0;
        if (gasLevel <= 0){
            gasLevel =0;
            System.out.println("Game Over!");
        }
        drawScoreBox(gc,roundedGasLevel);

    }

    private void hitPause(){
        isPaused = !isPaused;

        if (isPaused){
            timeline.pause();
            showPausedMessage();
        }else{
            timeline.play();
            removePauseMessage();
        }
    }

    private void showPausedMessage( ){
        if (pauseButton == null){
            pauseButton = new Button("Go");
            pauseButton.setFont(Font.font(60));
            pauseButton.setFont(Font.font("Arial", FontWeight.BOLD,20));
            pauseButton.setTextFill(Color.RED);
            pauseButton.setLayoutX(Const.WINDOW_WIDTH /2-100);
            pauseButton.setLayoutY(Const.WINDOW_HEIGHT/2-100);
            pauseButton.setOnMouseClicked(mouseEvent -> hitPause());
            this.getChildren().add(pauseButton);
        }
    }

    private void removePauseMessage(){
        if (pauseButton != null){
            this.getChildren().remove(pauseButton);
            pauseButton = null;
        }
    }

    private void showGameOverScreen(GraphicsContext gc) {
        if (gameOverText == null) {
            gameOverText = new Text("Game Over!");
            gameOverText.setFont(Font.font(60));
            gameOverText.setFont(Font.font("Arial", FontWeight.BOLD,20));
            gameOverText.setX(Const.WINDOW_WIDTH / 2 - 100);
            gameOverText.setY(Const.WINDOW_HEIGHT / 2 - 100);
            this.getChildren().add(gameOverText);
        }

        if (buttonsLayout == null) {
            buttonsLayout = new VBox(10);
            buttonsLayout.setAlignment(Pos.CENTER);
            buttonsLayout.setSpacing(20);

            // Play Again Button
            Button playAgainButton = new Button("Play Again");
            playAgainButton.setOnAction(e -> {
                resetGame();
            });
            playAgainButton.setTextFill(Color.WHITE);
            playAgainButton.setFont(Font.font(60));
            playAgainButton.setFont(Font.font("Arial", FontWeight.BOLD,20));
            buttonsLayout.getChildren().add(playAgainButton);

            // Main Menu Button
            Button mainMenuButton = new Button("Main Menu");
            mainMenuButton.setOnAction(e -> goToMainMenu());
            mainMenuButton.setFont(Font.font(60));
            mainMenuButton.setFont(Font.font("Arial",FontWeight.BOLD,20));
            mainMenuButton.setTextFill(Color.WHITE);
            buttonsLayout.getChildren().add(mainMenuButton);
            this.getChildren().add(buttonsLayout);  // Add buttons to the pane
        }
    }

    private void resetGame() {
        // Reset all game variables
        gasLevel = 100.0;
//        car.setCoinCount(ScoreSystem.getStoredScore());
        car.setGasCount(0);
        coins.clear();
        fuels.clear();

        // Remove the "Game Over" text and buttons
      if (gameOverText != null){
          this.getChildren().remove(gameOverText);
          gameOverText = null;
      }

      if (buttonsLayout != null){
          this.getChildren().remove(buttonsLayout);
          buttonsLayout = null;
      }

        isGameOver = false;
        System.out.println("Game has been reset");
    }

    private void goToMainMenu(){
        /// Replace with actual main menu scene
        LateNightCruise.mainStage.setScene(new MainMenuScene());
    }

    private void drawScoreBox(GraphicsContext gc, double roundedGasLevel){
        ///  Coin box
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
        gc.fillText("Gas: "+ car.getGasCount(),Const.WINDOW_WIDTH -140,230);
        String formattedGasLevel = String.format("%.2f", roundedGasLevel);
        gc.fillText("gas Level:" + roundedGasLevel, Const.WINDOW_WIDTH -140,250);
    }
}





















