package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import com.gearshifgroove.late_night_cruise.panes.Store.PlaylistListing;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

// Author(s): Christian Moloci

// A sub playlist view, diplays all the playlists with logic to add a song to the playlist when a playlist listing is clicked
// This class extends BorderPane
public class AddToPlaylistView extends BorderPane {
    // Must have a song object
    private final Song song;

    public AddToPlaylistView(Song song) {
        // Initializes song object
        this.song = song;

        // Display a header title explaining what the user should do
        Text title = new Text("Select a playlist:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        this.setTop(title);

        // Get current playlist data
        ListView<PlaylistListing> playlists = new ListView();
        playlists.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);

        // Convert playlist data into PlaylistListings and add them to a ListView
        ArrayList<Playlist> availablePlaylists = UserLib.getPlaylists();
        for (Playlist playlist : availablePlaylists) {
            playlists.getItems().add(new PlaylistListing(playlist));
        }

        // Set the center of the borderpane to the Playlists list view
        this.setCenter(playlists);

        // Update corresponding playlist object in playlists ListView which also updates the ArrayList and then overwrite the playlist.dat file with the updated playlist objects
        playlists.setOnMouseClicked(event -> {
            try {
                // Flag for if song is already in the playlist
                boolean songExists = false;
                // Get the selected playlist in the list view
                Playlist currentPlaylist = playlists.getSelectionModel().getSelectedItem().getPlaylist();
                // Check if song exists and if it does, flag it so we can prevent it from being added
                for (Song sng : currentPlaylist.getSongs()) {
                    if (song.getId().equals(sng.getId())) {
                        songExists = true;
                        break;
                    }
                }
                // If the song is not yet in the playlist, we can add it
                if (!songExists) {
                    // Start by adding the song to the playlist in the playlist ListView
                    playlists.getSelectionModel().getSelectedItem().getPlaylist().addSong(song);
                    try {
                        // Create and object input stream and reference the playlists data file
                        ObjectOutputStream  overwritePlaylists = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
                        // loop through the playlists and overwrite all the playlists in the playlist file the playlists in the list view
                        // including the newly modified playlist
                        for (Playlist playlist : availablePlaylists) {
                            overwritePlaylists.writeObject(playlist);
                        }
                        // Close the ObjectOutputStream
                        overwritePlaylists.close();
                    } catch (Exception e)  {
                        e.printStackTrace();
                    } // Log the error if an error occurs
                } else {
                    System.out.println("Song already added to playlist");
                } // If the song is already in the playlist, don't allow the user to add it and log an error
//                if (currentPlaylist.getName().equals(GlobalPlayer.selectedPlaylist.getName())) {
//                    GlobalPlayer.selectedPlaylist = currentPlaylist;
//                }
            } catch (Exception e) {
                e.printStackTrace();
            } // Log the error if an error occurs
        });

        // Set the min width to the maximum width that can fit
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
    }
}
