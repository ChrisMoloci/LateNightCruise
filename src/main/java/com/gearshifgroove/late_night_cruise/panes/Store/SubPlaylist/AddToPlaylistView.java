package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

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

public class AddToPlaylistView extends BorderPane {
    private final Song song;
    public AddToPlaylistView(Song song) {
        this.song = song;
        Text title = new Text("Select a playlist:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        this.setTop(title);

        // Get current playlist data
        ListView<PlaylistListing> playlists = new ListView();

        // Convert playlist data into PlaylistListings and add them to a ListView
        ArrayList<Playlist> availablePlaylists = UserLib.getPlaylists();
        for (Playlist playlist : availablePlaylists) {
            playlists.getItems().add(new PlaylistListing(playlist));
        }

        this.setCenter(playlists);

        // Update corresponding playlist object in playlists ListView which also updates the ArrayList and then overwrite the playlist.dat file with the updated playlist objects
        playlists.setOnMouseClicked(event -> {
            try {
                boolean songExists = false;
                Playlist currentPlaylist = playlists.getSelectionModel().getSelectedItem().getPlaylist();
                for (Song sng : currentPlaylist.getSongs()) {
                    if (song.getId().equals(sng.getId())) {
                        songExists = true;
                        break;
                    }
                }
                if (!songExists) {
                    playlists.getSelectionModel().getSelectedItem().getPlaylist().addSong(song);
                    try {
                        ObjectOutputStream  overwritePlaylists = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
                        for (Playlist playlist : availablePlaylists) {
                            overwritePlaylists.writeObject(playlist);
                        }
                        overwritePlaylists.close();
                    } catch (Exception e)  {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Song already added to playlist");
                }
                if (currentPlaylist.getName().equals(GlobalPlayer.selectedPlaylist.getName())) {
                    GlobalPlayer.selectedPlaylist = currentPlaylist;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
