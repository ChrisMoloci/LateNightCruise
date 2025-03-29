package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.*;
import com.gearshifgroove.late_night_cruise.CustomUIElements.CustomButton;
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

// Author(s): Christian Moloci

// The Game pane is built on a StackPane
public class GamePane extends StackPane {
    // Vars
//    public static AudioClip carMedia;
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
    private Button unpauseButton;
    private Button mainMenuButton;
    private VBox buttonsLayout;
    private boolean isGameOver = false;
    private boolean isPaused= false;
    private VBox pauseMenu;

    /// Creating gas level
    private double gasLevel = 99.9;

    // Main Constructor
    public GamePane() {
        // Set the title to Late Night Cruise when playing the game
        LateNightCruise.mainStage.setTitle("Late Night Cruise");
        System.out.println("Game Started");
        // Set our assets
        terrainImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/track.png"));
        carImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/car.png"));
        coinImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/coin.png"));
        fuelImage = new Image(getClass().getResourceAsStream("/com/gearshifgroove/late_night_cruise/gas-can.png"));

        // Set our tiles with our asset references
        terrainTile1 = new Tile(0, 0, 0, 3, terrainImage);
        terrainTile2 = new Tile(0, 1080, 0, 3, terrainImage);
        car = new Player(883, 490, carImage);
        car.setCoinCount(ScoreSystem.getStoredScore());

        // Car sound (Not used, buggy)
//        carMedia = new AudioClip(new File("src/main/resources/com/gearshifgroove/late_night_cruise/carSound.wav").toURI().toString());
//        carMedia.setCycleCount(AudioClip.INDEFINITE);
//        carMedia.setVolume(0.1);
//        carMedia.play();

        // Debug why button must be present for keyboard input to work
        Button button = new Button("Start");
        button.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
        });

        this.getChildren().add(button);

        // Create a canvas with a 1080p resolution
        Canvas canvas = new Canvas(1920, 1080);
        gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();

        // Get keyboard input and do the respective tasks
        this.setOnKeyPressed(event -> {
            // Pauses the game
            if (event.getCode() == KeyCode.BACK_SPACE){
                hitPause();
            }

            if (isPaused){
                // If the game is paused, ignore other key event
                return;
            }
            // For contorting player left and right, just sets values for now
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

        // Create a timeline and set the frame rate to 60fps, set it to repeat indefinitely and play it
        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> update(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Add canvas to the parent stack pane
        this.getChildren().add(canvas);
    }

    /// This will generate a random lane for the coin or fuel
    private int getRandomLane(){
        /// Possible lanes
//        int[] lanes = {800,900,982,1068};
        int[] lanes = {819,907,995,1083};
        /// Randomly choose one lane
        return lanes[rand.nextInt(lanes.length)];
        //return rand.nextInt(maxX-minX + 1) + minX;
    }

    public void update(GraphicsContext gc) {
        if (isGameOver){
            showGameOverScreen(gc);
            return;
        }

//        int key = rand.nextInt(100);
//
//        if (key == 0) {
//            key = rand.nextInt(0, 2);
//            if (key == 0) {
//                System.out.println("Coin");
//            } else if (key == 1) {
//                System.out.println("Fuel");
//            } else {
//                System.out.println("Unknown generator");
//            }
//
//        }

        // Clear the screen to redraw everything after
        gc.clearRect(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        // Shift the terrain locations
        terrainTile1.setyCoord(terrainTile1.getyCoord() + terrainTile1.getyCordSpeed());
        terrainTile2.setyCoord(terrainTile2.getyCoord() + terrainTile2.getyCordSpeed());

        // If the direction value changes, move the player in the respective direction and reset the direction back to N
        if (direction != 'N') {
            if (direction == 'L') {
                car.moveLeft();
            }
            if (direction == 'R') {
                car.moveRight();
            }
            direction = 'N';
        }

        // Set the cars new x coord (if they changed that is)
        car.setxCoord(car.getxCoord() + car.getxCordSpeed());

        // If a tile goes off the screen, move it to the top of the screen
        if (terrainTile1.getyCoord() > 1080) {
            terrainTile1.setyCoord(-1080);
        }
        if (terrainTile2.getyCoord() > 1080) {
            terrainTile2.setyCoord(-1080);
        }

        // Draw the terrain tiles
        gc.drawImage(terrainTile1.getImage(), terrainTile1.getxCoord(), terrainTile1.getyCoord());
        gc.drawImage(terrainTile2.getImage(), terrainTile2.getxCoord(), terrainTile2.getyCoord());

        // Draw the car tile
        gc.drawImage(car.getImage(), car.getxCoord(), car.getyCoord());

        /// Coin & Fuel
        /// Random number 1 and 1000
        int randNum = rand.nextInt(1000) +1;
        /// Random number between 1 and 2
        int spawnType = rand.nextInt(2)+1;

            /// If both numbers match spawn a coin or fuel
        if (randNum >= 995){
            int randomLane = getRandomLane();
            if (spawnType==1){
                Coin newCoin = new Coin(randomLane,-50,coinImage);
                coins.add(newCoin);
            }
            else if (spawnType==2) {
                Fuel newFuel = new Fuel(randomLane,-50,fuelImage);
                fuels.add(newFuel);
            }
        }

        for(int i=0; i<coins.size(); i++){
            Coin coin = coins.get(i);
            /// Moving it downwards by 2 pixels per frame
            coin.setyCoord(coin.getyCoord()+2);

            /// Check if the coin has moved off the screen, if y is greater than 1080, which is the bottom
            if (coin.getyCoord()>1080){
                coins.remove(i);
        }

            if (Math.abs(car.getxCoord() - coin.getxCoord())<30 && Math.abs(car.getyCoord()-coin.getyCoord())<30){
                ScoreSystem.updateStoredScore(ScoreSystem.getStoredScore() + 1);
                car.setCoinCount(ScoreSystem.getStoredScore());
//                car.setCoinCount(car.getCoinCount()+1);
                coins.remove(i);
                //i--;
            }

            gc.drawImage(coin.getImage(),coin.getxCoord(),coin.getyCoord());
        }

        for (int i=0; i<fuels.size(); i++){
            Fuel fuel = fuels.get(i);
            fuel.setyCoord(fuel.getyCoord() + 2);

            /// Check if the fuel reaches bottom or collected
            if (fuel.getyCoord()>1080){
                fuels.remove(i);
            }

            if (Math.abs(car.getxCoord() - fuel.getxCoord())<30 && Math.abs(car.getyCoord() - fuel.getyCoord())<30){
                car.setGasCount(car.getGasCount()+1);
                gasLevel += 5;
                if (gasLevel > 100){
                    gasLevel =100;
                }
                fuels.remove(i);
                //i--;

            }
            gc.drawImage(fuel.getImage(),fuel.getxCoord(), fuel.getyCoord());
        }

        if (gasLevel >0){
            gasLevel -=0.03;
        } else{
            gasLevel =0;
            isGameOver = true;
        }

        /// Round the gas level
        int roundedGasLevel = (int) Math.floor(gasLevel);
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

            if (buttonsLayout == null){
                buttonsLayout = new VBox(10);
                buttonsLayout.setAlignment(Pos.CENTER);
                buttonsLayout.setSpacing(20);

//                unpauseButton = new Button("Unpause");
                unpauseButton = new CustomButton("Resume", Font.font("Arial", FontWeight.BOLD, 20), 150, 60, Color.rgb(0, 112, 40), Color.WHITE);
                unpauseButton.setOnAction(e->hitPause());
//                unpauseButton.setFont(Font.font("Arial", FontWeight.BOLD,20));
//                unpauseButton.setTextFill(Color.WHITE);
                buttonsLayout.getChildren().add(unpauseButton);

//                mainMenuButton = new Button("Main Menu");
                mainMenuButton = new CustomButton("Main Menu", Font.font("Arial", FontWeight.BOLD, 20), 150, 60, Color.rgb(250, 250, 250), Color.BLACK);
                mainMenuButton.setOnAction(e->goToMainMenu());
//                mainMenuButton.setFont(Font.font("Arial",FontWeight.BOLD,20));
//                mainMenuButton.setTextFill(Color.WHITE);
                buttonsLayout.getChildren().add(mainMenuButton);

                this.getChildren().add(buttonsLayout);
            }
        }else{
            timeline.play();
            if (buttonsLayout != null){
                this.getChildren().remove(buttonsLayout);
                buttonsLayout = null;
            }
        }
    }

    private void showGameOverScreen(GraphicsContext gc) {
        if (gameOverText == null) {
            gameOverText = new Text("Game Over!");
            gameOverText.setFont(Font.font(60));
            gameOverText.setFont(Font.font("Arial", FontWeight.BOLD,50));
            gameOverText.setFill(Color.WHITE);
            gameOverText.setX(Const.WINDOW_WIDTH / 2 - 100);
            gameOverText.setTranslateY(Const.WINDOW_HEIGHT/2-400);
            //gameOverText.setY(Const.WINDOW_HEIGHT / 2 - 400);
            this.getChildren().add(gameOverText);
        }

        if (buttonsLayout == null) {
            buttonsLayout = new VBox(10);
            buttonsLayout.setAlignment(Pos.CENTER);
            buttonsLayout.setSpacing(20);

            // Play Again Button
//            Button playAgainButton = new Button("Play Again");
            Button playAgainButton = new CustomButton("Play Again", Font.font("Arial", FontWeight.BOLD, 20), 150, 60, Color.rgb(0, 112, 40), Color.WHITE);
            playAgainButton.setOnAction(e -> {
                resetGame();
            });
//            playAgainButton.setTextFill(Color.WHITE);
//            playAgainButton.setFont(Font.font(60));
//            playAgainButton.setFont(Font.font("Arial", FontWeight.BOLD,20));
            buttonsLayout.getChildren().add(playAgainButton);

            // Main Menu Button
//            Button mainMenuButton = new Button("Main Menu");
            Button mainMenuButton = new CustomButton("Main Menu", Font.font("Arial", FontWeight.BOLD, 20), 150, 60, Color.rgb(250, 250, 250), Color.BLACK);
            mainMenuButton.setOnAction(e -> goToMainMenu());
//            mainMenuButton.setFont(Font.font(60));
//            mainMenuButton.setFont(Font.font("Arial",FontWeight.BOLD,20));
//            mainMenuButton.setTextFill(Color.WHITE);
            buttonsLayout.getChildren().add(mainMenuButton);

            this.getChildren().add(buttonsLayout);
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
        double boxWidth = 150;
        double boxHeight = 84;

        double boxX = Const.WINDOW_WIDTH - boxWidth + 800;
        double boxY = 100;

        gc.setFill(Color.BLACK);
        gc.fillRect(boxX, boxY, boxWidth, boxHeight);

        gc.setFill(Color.WHITE);

        gc.setFont(Font.font("Arial",FontWeight.BOLD,14));

        gc.fillText("Coin: "+ car.getCoinCount(),boxX + 10, boxY + 30);
        //gc.fillText("Gas: " + car.getGasCount(), boxX + 10, boxY + 50);

//        String formattedGasLevel = String.format("%.2f", roundedGasLevel);
        gc.fillText("gas Level:" + Math.round(roundedGasLevel), boxX + 10, boxY + 60);
    }
}





















