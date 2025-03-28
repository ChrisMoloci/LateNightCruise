package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;

// Author(s): Christian Moloci

// A view that displays all songs available for purchase as a list of custom nodes called song listings
public class AllSongs extends ScrollPane {
    public AllSongs() {
        // Create a VBox to store all the song listings in
        VBox topSongsBox = new VBox();

        // Store the owned songs in a playlist to check for ownership and pass its ownership state to the listing
        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        // Loop through all the artists
        for (Artist artist : DB.getArtists().values()) {
            // Loop through the artist's songs
            for (Song song : artist.getSongs()) {
                // Ownership flag
                boolean songOwned = false;
                // Loop through the owned songs and check if the song is owned
                for (String ownedSong : ownedSongs) {
                    // If the song is owned, change the ownership flag and stop looping
                    if (song.getId().equals(ownedSong)) {
                        songOwned = true;
                        break;
                    }
                }
                // If the song is owned, create an owned song listing, otherwise, create a non-owned listing
                if (songOwned) {
                    topSongsBox.getChildren().add(new SongListing(song, true));
                } else {
                    topSongsBox.getChildren().add(new SongListing(song, false));
                }
            }
        }

        // Add the VBox to the scroll pane
        this.setContent(topSongsBox);

        // Set the sizing of the scroll pane
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
    }

}
