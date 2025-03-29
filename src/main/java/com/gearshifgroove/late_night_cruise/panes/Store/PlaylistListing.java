package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Author(s): Christian Moloci

// A custom node thar displays info about a playlist and allows for some actions to be done to it
public class PlaylistListing extends GridPane {
    // Create a playlist object
    private static Playlist playlist;
    public PlaylistListing(Playlist playlist) {
        // Init the playlist object by setting it to the passed in playlist
        this.playlist = playlist;

        // Set the styling
        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), new CornerRadii(10), new Insets(3, 5, 3, 5))));
        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), null, new Insets(3, 5, 3, 5))));
        this.setPadding(new Insets(10, 10, 10, 10));

        // Create column constraints
        ColumnConstraints select = new ColumnConstraints();
        select.setPercentWidth(20);
        ColumnConstraints playlistInfo = new ColumnConstraints();
        playlistInfo.setPercentWidth(64);
        ColumnConstraints delete = new ColumnConstraints();
        delete.setPercentWidth(8);
        ColumnConstraints view = new ColumnConstraints();
        view.setPercentWidth(8);

        // Set column constraints
        this.getColumnConstraints().addAll(select, playlistInfo, delete, view);

        // Create a select button
        Button selectButton = new Button("Select Playlist");
        selectButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        selectButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        selectButton.setTextFill(Color.WHITE);

        // Create a playlistName node to display the name of the playlist
        Text playlistName = new Text(playlist.getName());
        playlistName.setFill(Color.WHITE);

        // Create a delete button
        Button deleteButton = new Button("X");
        deleteButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        deleteButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        deleteButton.setTextFill(Color.WHITE);

        // Create a view button
        Button viewButton = new Button(">");
        viewButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        viewButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        viewButton.setTextFill(Color.WHITE);

        // Delete button deletes the playlist from the playlists.dat file
        deleteButton.setOnAction(e -> {
            // Get the current playlists
            ArrayList<Playlist> oldPlaylists = UserLib.getPlaylists();
            // Attempt removal
            try {
                // Create ObjectOutputStream and tell it to work with playlist.dat
                ObjectOutputStream excludePlaylistObjectWriter = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
                // Overwrite playlist.dat with all the playlists except the one being removed
                for (Playlist p : oldPlaylists) {
                    if (!p.getName().equals(playlist.getName())) {
                        excludePlaylistObjectWriter.writeObject(p);
                    }
                }
                // Close the ObjectOutputStream
                excludePlaylistObjectWriter.close();
                // If the remove playlist is also the selected one, change the selectedPlaylist to the internal demoPlaylist object
                if (GlobalPlayer.selectedPlaylist.getName().equals(playlist.getName())) {
                    GlobalPlayer.selectedPlaylist = DB.demoPlaylist;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } // Runs if an error occurs
            // Refresh the Playlists screen
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new Playlists());
        });

        // Selects the playlist to be played in the game
        selectButton.setOnAction(e -> {
            // Displays the songs that are in a playlist in the console (For Debugging)
            System.out.println("Songs in playlist: ");
            for (Song song : playlist.getSongs()) {
                System.out.println(song.getSongName());
            }
            // Set selected playlist to the playlist object
            GlobalPlayer.selectedPlaylist = playlist;
            // Set the selectedPlaylist file to the playlist name so that if the game is re-run, it remembers the selected playlist
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("selectedPlaylist.txt"));
                writer.write(playlist.getName());
                writer.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            } // Runs if an error occurs
        });

        // Opens a new page that displays all the songs in the playlist as song Listings
        viewButton.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new PlaylistSongs(playlist));
        });

        // Add all the nodes to the GridPane
        this.add(selectButton, 0, 0, 1, 1);
        this.add(playlistName, 1, 0);
        this.add(deleteButton, 2, 0, 1, 1);
        this.add(viewButton, 3, 0, 1, 1);

        // Set the dimensions of the grid pane
        this.setMaxWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4 - 18);
    }

    // Function to get the playlists
    public Playlist getPlaylist() {
        return playlist;
    }
}
