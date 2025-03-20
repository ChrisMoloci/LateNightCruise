package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.CustomUIElements.CustomButton;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.scenes.ItemShopScene;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Songs;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
//import com.gearshifgroove.late_night_cruise.CustomUIElements.CustomButton;

import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainMenuPane extends BorderPane {
    public MainMenuPane() {
////        ScoreSystem.updateStoredScore(10);
//        System.out.println(ScoreSystem.getStoredScore());
//        GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0001").getMedia());
//
//        VBox buttons = new VBox();
//
//        Button playButton = new Button("Start");
//        Button storeButton = new Button("Store");
//        Button itemShopButton = new Button("Item Shop");
//
//        playButton.setOnAction(event -> {
//            LateNightCruise.mainStage.setScene(new GameScene());
////            GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0002").getMedia());
//            GlobalPlayer.playPlaylist(GlobalPlayer.selectedPlaylist, 0);
//        });
//
//        storeButton.setOnAction(event -> {
//            LateNightCruise.mainStage.setScene(new StoreScene());
//            GlobalPlayer.stopMedia();
//        });
//
//        itemShopButton.setOnAction(e->{
//            LateNightCruise.mainStage.setScene(new ItemShopScene());
//            GlobalPlayer.stopMedia();
//        });
//
//        buttons.getChildren().addAll(playButton, storeButton,itemShopButton);
//        buttons.setAlignment(Pos.CENTER);
//
//        this.setCenter(buttons);
//        Pane root = new Pane();
        this.setStyle("-fx-background-color: linear-gradient(to bottom, #ff00ff, #0000ff, #001f3f);"); // Vibrant neon background
//        this.setBackground(new Background(new BackgroundFill(new LinearGradient(to bottom, #ff00ff, #0000ff, #001f3f))));


        // Background neon glow effect
        Rectangle glow = new Rectangle(1024, 768, Color.TRANSPARENT);
        glow.setStroke(Color.CYAN);
        glow.setStrokeWidth(10);
        glow.setEffect(new DropShadow(50, Color.BLUE));

        // Project
        Text title = new Text("LATE NIGHT CRUISE");
        title.setFont(Font.font("Arial", 70));
        title.setFill(Color.GOLD);
        title.setEffect(new DropShadow(10, Color.BLACK));
//        title.setX(200);
//        title.setY(200);
        BorderPane.setAlignment(title, Pos.CENTER);

        VBox top = new VBox();
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(new PaddingBox(50, 0), title);

        VBox buttons = new VBox();

        CustomButton play = new CustomButton("Play", new Font("Arial", 20), 250, 50, Color.RED, Color.WHITE);
        CustomButton store = new CustomButton("Store", new Font("Arial", 20), 250, 50, Color.rgb(255, 117, 0), Color.WHITE);
        CustomButton settings = new CustomButton("Settings", new Font("Arial", 20), 250, 50, Color.rgb(228, 228, 228), Color.WHITE);

        play.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
//            GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0002").getMedia());
            GlobalPlayer.playPlaylist(GlobalPlayer.selectedPlaylist, 0);
        });

        store.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new StoreScene());
            GlobalPlayer.stopMedia();
        });

        settings.setOnAction(e->{
            LateNightCruise.mainStage.setScene(new ItemShopScene());
            GlobalPlayer.stopMedia();
        });

        buttons.getChildren().addAll(play, store, settings);

        buttons.setSpacing(10);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        // Animations
        FadeTransition fadeInTitle = new FadeTransition(Duration.seconds(2), title);
        fadeInTitle.setFromValue(0);
        fadeInTitle.setToValue(1);
        fadeInTitle.play();

        TranslateTransition slideTitle = new TranslateTransition(Duration.seconds(2), title);
        slideTitle.setFromY(-100);
        slideTitle.setToY(0);
        slideTitle.play();

        ScaleTransition expandGlow = new ScaleTransition(Duration.seconds(3), glow);
        expandGlow.setFromX(0.8);
        expandGlow.setFromY(0.8);
        expandGlow.setToX(1);
        expandGlow.setToY(1);
        expandGlow.play();

        this.getChildren().addAll(glow);
        this.setTop(top);
        this.setCenter(buttons);
//        Scene scene = new Scene(root, 1024, 768);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Retro Night Racer - Intro");
//        primaryStage.show();
    }
}
