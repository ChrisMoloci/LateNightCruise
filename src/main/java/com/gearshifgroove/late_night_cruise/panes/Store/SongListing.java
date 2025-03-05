package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.AddToPlaylistView;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
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
        songInfo.setPercentWidth(85);
        ColumnConstraints songActions = new ColumnConstraints();
        songActions.setPercentWidth(7);

        this.getColumnConstraints().addAll(play, songInfo, songActions);

        Button playButton = new Button("Play");

        Text songName = new Text(song.getSongName());
        Text artistName = new Text("Artist: ");

        Button addToPlaylistButton = new Button("+");

        playButton.setOnAction(e -> {
            GlobalPlayer.changeSong(song.getMedia());
        });

        addToPlaylistButton.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new AddToPlaylistView(song));
        });

        this.add(playButton, 0, 0, 1, 1);
        this.add(songName, 1, 0);
        this.add(artistName, 1, 1);
        this.add(addToPlaylistButton, 2, 0);
        this.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), null, null)));
//        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH/4);
    }
}
