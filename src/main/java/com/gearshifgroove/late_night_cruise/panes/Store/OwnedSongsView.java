package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class OwnedSongsView extends ScrollPane {
    public OwnedSongsView() {
        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        VBox content = new VBox();

        for(Artist artist : DB.getArtists().values()) {
            for (Song song : artist.getSongs()) {
                for (String songID : ownedSongs) {
                    if (song.getId().equals(songID)) {
                        content.getChildren().add(new SongListing(song, true));
                    }
                }
            }
        }

        this.setContent(content);
        this.setMaxHeight(Const.WINDOW_HEIGHT);
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
    }
}
