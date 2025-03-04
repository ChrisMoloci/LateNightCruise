package com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist;

import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import com.gearshifgroove.late_night_cruise.panes.Store.PlaylistListing;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class AddToPlaylistView extends BorderPane {
    public AddToPlaylistView() {
        Text title = new Text("Select a playlist:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        this.setTop(title);

        ListView playlists = new ListView();
        ArrayList<Playlist> availablePlaylists = UserLib.getPlaylists();
        for (Playlist playlist : availablePlaylists) {
            playlists.getItems().add(new PlaylistListing(playlist));
        }
        this.setCenter(playlists);
//        playlists.setOnMouseClicked(event -> {
//            System.out.println(playlists.getSelectionModel().getSelectedItem());
//        });
    }
}
