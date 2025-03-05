package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

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

import java.util.ArrayList;

public class AddToPlaylistView extends BorderPane {
    private Song song;
    public AddToPlaylistView(Song song) {
        this.song = song;
        Text title = new Text("Select a playlist:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        this.setTop(title);

        ListView<PlaylistListing> playlists = new ListView();
        ArrayList<Playlist> availablePlaylists = UserLib.getPlaylists();
        for (Playlist playlist : availablePlaylists) {
            playlists.getItems().add(new PlaylistListing(playlist));
        }
        playlists.getSelectionModel().select(0);
        this.setCenter(playlists);

        playlists.setOnMouseClicked(event -> {
            try {
                System.out.println(playlists.getSelectionModel().getSelectedIndex());
//            playlists.getSelectionModel().getSelectedItem().getPlaylist().addSong(song);
//            System.out.println("Added to playlist: " + playlists.getSelectionModel().getSelectedItem().getPlaylist());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        try {
            System.out.println(playlists.getSelectionModel().getSelectedIndex());
//            playlists.getSelectionModel().getSelectedItem().getPlaylist().addSong(song);
//            System.out.println("Added to playlist: " + playlists.getSelectionModel().getSelectedItem().getPlaylist());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        playlists.setOnMouseClicked(event -> {
//            System.out.println(playlists.getSelectionModel().getSelectedItem());
//        });
    }
}
