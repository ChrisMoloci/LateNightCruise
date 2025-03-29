package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

// Author(s): Christian Moloci

// Displays all the songs in a particular playlists as PlaylistSongListing nodes
public class PlaylistSongs extends ScrollPane {
    public PlaylistSongs(Playlist playlist) {
        // Create an ArrayList of all the songs in the passed in playlist object
        ArrayList<Song> songs = playlist.getSongs();

        // Set the background of the ScrollPane
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Create a VBox to store all the PlaylistSongListing nodes in
        VBox content = new VBox();
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        content.setMinHeight(Const.WINDOW_HEIGHT);

        // Create a PlaylistSongListing for each song in the songs array and add it to the content VBox
        for (Song song : songs) {
            content.getChildren().add(new PlaylistSongListing(song, Ownership.checkOwnership(song.getId()), playlist));
        }

        // Set the content of the ScrollPane to the content VBox
        this.setContent(content);

        // Set the dimensions of the Parent ScrollPane
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
    }
}
