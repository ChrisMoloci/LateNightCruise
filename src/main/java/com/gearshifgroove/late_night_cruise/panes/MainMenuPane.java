package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.scenes.ItemShopScene;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Songs;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;

import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuPane extends BorderPane {
    public MainMenuPane() {
//        ScoreSystem.updateStoredScore(10);
        System.out.println(ScoreSystem.getStoredScore());
        GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0001").getMedia());

        VBox buttons = new VBox();

        Button playButton = new Button("Start");
        Button storeButton = new Button("Store");
        Button itemShopButton = new Button("Item Shop");

        playButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
//            GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0002").getMedia());
            GlobalPlayer.playPlaylist(GlobalPlayer.selectedPlaylist, 0);
        });

        storeButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new StoreScene());
            GlobalPlayer.stopMedia();
        });

        itemShopButton.setOnAction(e->{
            LateNightCruise.mainStage.setScene(new ItemShopScene());
            GlobalPlayer.stopMedia();
        });

        buttons.getChildren().addAll(playButton, storeButton,itemShopButton);
        buttons.setAlignment(Pos.CENTER);

        this.setCenter(buttons);
    }
}
