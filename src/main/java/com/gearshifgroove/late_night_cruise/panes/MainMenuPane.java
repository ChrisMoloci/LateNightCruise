package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.CustomUIElements.CustomButton;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.scenes.*;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.*;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
//import com.gearshifgroove.late_night_cruise.CustomUIElements.CustomButton;

import com.gearshifgroove.late_night_cruise.scenes.SettingsScene;
import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;

// Author(s): Christian Moloci

public class MainMenuPane extends StackPane {
    public MainMenuPane() {
        LateNightCruise.mainStage.setTitle("Main Menu");
        GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0001").getMedia());

        // Setup responsive background
        setStyle("-fx-background-color: linear-gradient(to bottom, #ff00ff, #0000ff, #001f3f);");

        // Create responsive glow effect
        Rectangle glow = createGlowEffect();

        // Main content container
        VBox mainContent = createMainContent();

        // Setup animations
        setupAnimations(mainContent, glow);

        getChildren().addAll(glow, mainContent);
    }

    private Rectangle createGlowEffect() {
        Rectangle glow = new Rectangle();
        glow.setFill(Color.TRANSPARENT);
        glow.setStroke(Color.CYAN);
        glow.setStrokeWidth(8);  // Slightly reduced stroke width
        glow.setEffect(new DropShadow(40, Color.BLUE));  // Smaller shadow

        // Bind to parent size
        glow.widthProperty().bind(widthProperty());
        glow.heightProperty().bind(heightProperty());

        return glow;
    }

    private VBox createMainContent() {
        VBox content = new VBox(30);  // Reduced vertical spacing
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(30));  // Reduced padding

        // Title with responsive positioning
        Text title = createTitle();

        // Button panel with responsive layout
        VBox buttonPanel = createButtonPanel();

        content.getChildren().addAll(title, new PaddingBox(30, 0), buttonPanel);  // Reduced padding
        return content;
    }

    private Text createTitle() {
        Text title = new Text("LATE NIGHT CRUISE");
        title.setFont(Font.font("Arial", 60));  // Slightly smaller title
        title.setFill(Color.GOLD);
        title.setEffect(new DropShadow(8, Color.BLACK));  // Smaller shadow
        return title;
    }

    private VBox createButtonPanel() {
        VBox buttons = new VBox(15);  // Reduced button spacing
        buttons.setAlignment(Pos.CENTER);

        CustomButton play = createButton("Play", Color.rgb(0, 112, 40));
        CustomButton store = createButton("Store", Color.ORANGE);
        CustomButton settings = createButton("Settings", Color.LIGHTGRAY);
        settings.setTextFill(Color.BLACK);

        // Button actions
        play.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
            LateNightCruise.mainStage.setFullScreen(true);
            LateNightCruise.mainStage.setFullScreenExitHint(null);
            // Stop the global player (It's still playing the home menu music)
            GlobalPlayer.player.stop();
            // Init the music for the game
            GlobalPlayer.gameMusicInit();
        });

        store.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new StoreScene());
            GlobalPlayer.stopMedia();
            StorePane.mediaControl.setImage(StorePane.play);
        });

        settings.setOnAction(e -> {
            LateNightCruise.mainStage.setScene(new SettingsScene());
            GlobalPlayer.stopMedia();
        });

        buttons.getChildren().addAll(play, store, settings);
        return buttons;
    }

    private CustomButton createButton(String text, Color color) {
        CustomButton btn = new CustomButton(
                text,
                new Font("Arial", 18),  // Smaller font size
                160,  // Reduced width
                40,   // Reduced height
                color,
                Color.WHITE
        );
        btn.setMaxSize(160, 40);  // Constrain maximum size
        return btn;
    }

    private void setupAnimations(VBox content, Rectangle glow) {
        // Title animations
        Text title = (Text) content.getChildren().get(0);

        FadeTransition fadeInTitle = new FadeTransition(Duration.seconds(1.5), title);
        fadeInTitle.setFromValue(0);
        fadeInTitle.setToValue(1);

        TranslateTransition slideTitle = new TranslateTransition(Duration.seconds(1.5), title);
        slideTitle.setFromY(-80);  // Reduced animation distance
        slideTitle.setToY(0);

        // Glow animation
        Timeline glowPulse = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.opacityProperty(), 0.6),
                        new KeyValue(glow.strokeWidthProperty(), 6)
                ),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(glow.opacityProperty(), 0.2),
                        new KeyValue(glow.strokeWidthProperty(), 10)
                )
        );
        glowPulse.setCycleCount(Animation.INDEFINITE);
        glowPulse.setAutoReverse(true);

        // Play animations
        new ParallelTransition(fadeInTitle, slideTitle).play();
        glowPulse.play();
    }
}