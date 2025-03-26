package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PlaylistSongs extends ScrollPane {
    public PlaylistSongs(ArrayList<Song> songs) {
        VBox content = new VBox();
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        content.setMinHeight(Const.WINDOW_HEIGHT);

        for (Song song : songs) {
            content.getChildren().add(new SongListing(song, Ownership.checkOwnership(song.getId())));
        }

        this.setContent(content);
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
    }
}
