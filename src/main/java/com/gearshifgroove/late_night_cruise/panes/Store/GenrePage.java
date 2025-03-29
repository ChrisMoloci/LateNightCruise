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

// Displays all the songs in the genre using SongListing nodes
public class GenrePage extends ScrollPane {
    // Takes in a list of filtered songs
    public GenrePage(ArrayList<Song> songs) {
        // A VBox to store the song listing nodes
        VBox genreSongsBox = new VBox();

        // Get the owned songs
        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        // Display nodes with either the ability to purchase a song or add to a playlist based on ownership value
        for (Song song : songs) {
            // Check it a song is owned
            boolean songOwned = false;
            for (String ownedSong : ownedSongs) {
                if (song.getId().equals(ownedSong)) {
                    songOwned = true;
                    break;
                }
            }
            // Create a song listing either as purchasable or playlist addable based on songOwned value
            genreSongsBox.getChildren().add(new SongListing(song, songOwned));
        }

        // Set the content of the scrollpane to the genresPane VBox
        this.setContent(genreSongsBox);

        // Set all the dimensions
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
    }
}
