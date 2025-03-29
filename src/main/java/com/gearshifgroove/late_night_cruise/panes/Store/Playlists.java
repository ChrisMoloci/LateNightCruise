package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.*;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.CreatePlaylistView;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;

// Author(s): Christian Moloci

// Show a list of all the playlists
public class Playlists extends VBox {
    public Playlists() {
        // A button for creating playlists, styled
        Button createPlaylist = new Button("Create Playlist");
        createPlaylist.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        createPlaylist.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        createPlaylist.setTextFill(Color.WHITE);

        // Add the create playlist button
        this.getChildren().add(createPlaylist);

        // Scroll pane to store all the PlaylistListing VBox in
        ScrollPane scrollPane = new ScrollPane();

        // A VBox to store all our playlist listings in
        VBox playlists = new VBox();
        playlists.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // When the button is clicked, go to the CreatePlaylistView which guides the user through creating a new playlist
        createPlaylist.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new CreatePlaylistView());
        });

        // Create a new playlist listing for each of the users' playlists and add it to the playlists VBox
        for (Playlist playlist : UserLib.getPlaylists()) {
            playlists.getChildren().add(new PlaylistListing(playlist));
        }

        // Set the content of the scroll pane as the playlists VBox, style the ScrollPane and set its sizing
        scrollPane.setContent(playlists);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        scrollPane.setMaxHeight(Const.WINDOW_HEIGHT);
        scrollPane.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        scrollPane.setMinHeight(0);
        this.setMaxWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);

        // Add the ScrollPane to the Parent VBox
        this.getChildren().add(scrollPane);

        // Set the dimensions of the parent VBox
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMaxWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMaxHeight(Const.WINDOW_HEIGHT);
    }
}
