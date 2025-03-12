package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.scenes.GameScene;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.scenes.StoreScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenuPane extends BorderPane {
    public MainMenuPane() {
        GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0001").getMedia());

        VBox buttons = new VBox();

        Button playButton = new Button("Start");
        Button storeButton = new Button("Store");

        playButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new GameScene());
//            GlobalPlayer.changeSong(DB.getArtists().get("0001").getSong("0002").getMedia());
            GlobalPlayer.playPlaylist(GlobalPlayer.selectedPlaylist, 0);
        });

        storeButton.setOnAction(event -> {
            LateNightCruise.mainStage.setScene(new StoreScene());
            GlobalPlayer.stopMedia();
        });

        buttons.getChildren().addAll(playButton, storeButton);
        buttons.setAlignment(Pos.CENTER);

        this.setCenter(buttons);
    }
}
