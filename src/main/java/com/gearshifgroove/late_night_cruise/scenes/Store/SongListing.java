package com.gearshifgroove.late_night_cruise.scenes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.scenes.Store.Data.Song;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SongListing extends GridPane {
    public SongListing(Song song) {
        ColumnConstraints play = new ColumnConstraints();
        play.setPercentWidth(8);
        ColumnConstraints songInfo = new ColumnConstraints();
        songInfo.setPercentWidth(92);

        this.getColumnConstraints().addAll(play, songInfo);

        Button playButton = new Button("Play");
        Text songName = new Text(song.getSongName());
        Text artistName = new Text("Artist: ");

        playButton.setOnAction(e -> {
            GlobalPlayer.changeSong(song.getMedia());
        });

        this.add(playButton, 0, 0, 1, 1);
        this.add(songName, 1, 0);
        this.add(artistName, 1, 1);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH/4);
    }
}
