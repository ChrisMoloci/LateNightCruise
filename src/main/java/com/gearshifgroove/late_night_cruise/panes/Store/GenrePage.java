package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GenrePage extends ScrollPane {
    public GenrePage(ArrayList<Song> songs) {
//        for (Song song : songs) {
//            System.out.println(song.getSongName());
//        }

        VBox genreSongsBox = new VBox();

        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        for (Song song : songs) {
            boolean songOwned = false;
            for (String ownedSong : ownedSongs) {
                if (song.getId().equals(ownedSong)) {
                    songOwned = true;
                    break;
                }
            }
            if (songOwned) {
                genreSongsBox.getChildren().add(new SongListing(song, true));
            } else {
                genreSongsBox.getChildren().add(new SongListing(song, false));
            }
        }

        this.setContent(genreSongsBox);
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
//        this.setMinSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
    }
}
