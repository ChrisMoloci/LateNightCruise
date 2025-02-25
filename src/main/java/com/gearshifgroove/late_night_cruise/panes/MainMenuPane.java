package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MainMenuPane extends BorderPane {
    public MainMenuPane() {
        Media media = new Media(new File(getClass().getResource("/com/gearshifgroove/late_night_cruise/Songs/0001.wav").toExternalForm()).toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();

        VBox buttons = new VBox();

        Button playButton = new Button("Start");
        Button storeButton = new Button("Store");

        playButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
        });

        storeButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new StoreScene());
        });

        buttons.getChildren().addAll(playButton, storeButton);
        buttons.setAlignment(Pos.CENTER);

        this.setCenter(buttons);
    }
}
