package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;


public class AllSongs extends ScrollPane {
    public AllSongs() {
        VBox topSongsBox = new VBox();

        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        for (Artist artist : DB.getArtists().values()) {
            for (Song song : artist.getSongs()) {
                System.out.println(song.getId());
//                this.getItems().add(new SongListing(song));
                boolean songOwned = false;
                for (String ownedSong : ownedSongs) {
                    if (song.getId().equals(ownedSong)) {
                        songOwned = true;
                        break;
                    }
                }
                if (songOwned) {
                    topSongsBox.getChildren().add(new SongListing(song, true));
                } else {
                    topSongsBox.getChildren().add(new SongListing(song, false));
                }
            }
        }

        this.setContent(topSongsBox);
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
//        this.setMaxHeight(Const.WINDOW_HEIGHT);
//
//        this.getItems().get(0);
//
//        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
//        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
//        this.setMinHeight(Const.WINDOW_HEIGHT);
////        this.setSelectionModel(null);
////        this.setEditable(false);
////        this.setFocusTraversable(false);
//
//        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
//        this.setFixedCellSize(USE_COMPUTED_SIZE);
    }

}
