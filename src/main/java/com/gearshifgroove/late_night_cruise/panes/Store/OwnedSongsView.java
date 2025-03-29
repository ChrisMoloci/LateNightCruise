package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

// Author(s): Christian Moloci

// Generates a ScrollPane of SongListings for songs the user owns
public class OwnedSongsView extends ScrollPane {
    public OwnedSongsView() {
        // Get the owned songs from Ownership
        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        // Create a VBox for content
        VBox content = new VBox();

        // Create a new song listing for each song that is owned in the ownedSongs ArrayList and add it o the content VBox
        for(Artist artist : DB.getArtists().values()) {
            for (Song song : artist.getSongs()) {
                for (String songID : ownedSongs) {
                    if (song.getId().equals(songID)) {
                        content.getChildren().add(new SongListing(song, true));
                    }
                }
            }
        }

        // Set the content of the ScrollPane to the content VBox
        this.setContent(content);

        // Set the dimensions
        this.setMaxHeight(Const.WINDOW_HEIGHT);
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
    }
}
