package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Songs;
import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MainMenuPane extends BorderPane {
    public MainMenuPane() {
        MediaPlayer player = new MediaPlayer(DB.getArtists().get("0001").getSong("0001").getMedia());
        player.setCycleCount(MediaPlayer.INDEFINITE);

        VBox buttons = new VBox();

        Button playButton = new Button("Start");
        Button storeButton = new Button("Store");

        playButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
            player.stop();
        });

        storeButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new StoreScene());
            player.stop();
        });

        buttons.getChildren().addAll(playButton, storeButton);
        buttons.setAlignment(Pos.CENTER);

        this.setCenter(buttons);
        player.play();
    }
}
