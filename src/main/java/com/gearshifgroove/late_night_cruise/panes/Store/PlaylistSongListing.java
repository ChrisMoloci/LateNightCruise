package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Playlist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.UserLib;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.AddToPlaylistView;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Author(s): Christian Moloci

// A modified version of a song listing that has an extran button for deleting the song from a playlist
// Only used inside playlist song views
public class PlaylistSongListing extends GridPane {
    // Boolean to store ownership status
    private boolean songOwned;

    // Main Constructor
    public PlaylistSongListing(Song song, boolean owned, Playlist playlist) {
//        System.out.println(song.getGenre().getName());

        // Init the song owned bool
        this.songOwned = owned;

        // Style the GridPane a bit
        this.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), null, new Insets(3, 5, 3, 5))));
        this.setPadding(new Insets(10, 0, 10, 0));

        // Create column constraints
        ColumnConstraints play = new ColumnConstraints();
        play.setPercentWidth(10);
        ColumnConstraints songInfo = new ColumnConstraints();
        songInfo.setPercentWidth(74);
        ColumnConstraints delete = new ColumnConstraints();
        delete.setPercentWidth(8);
        ColumnConstraints purchase = new ColumnConstraints();
        purchase.setPercentWidth(8);

        // Set column constraints
        this.getColumnConstraints().addAll(play, songInfo, delete, purchase);

        // Create a play button
        Button playButton = new Button("Play");
        playButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 3))));
        playButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        playButton.setTextFill(Color.WHITE);

        // A text node to display the song name
        Text songName = new Text(song.getSongName());
        songName.setFill(Color.WHITE);
        songName.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // A text node to display the name of the songs artist
        Text artistName = new Text("Artist: " + song.getArtist());
        artistName.setFill(Color.WHITE);
        artistName.setFont(Font.font("Arial", 12));

        // A button to delete the song from its context playlist
        Button deleteButton = new Button("X");
        deleteButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 0))));
        deleteButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        deleteButton.setTextFill(Color.WHITE);

        // A Button to add a song to a playlist/purchase is
        Button addToPlaylistButton = new Button();
        addToPlaylistButton.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(25), null, new Insets(3, 0, 3, 0))));
        addToPlaylistButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(25), null)));
        addToPlaylistButton.setTextFill(Color.WHITE);

        // Check for ownership and display the corresponding text
        if (songOwned) {
            addToPlaylistButton.setText("+");
        } else {
            addToPlaylistButton.setText("$10");
        }

        // Plays a song when clicked
        playButton.setOnAction(e -> {
            // GlobalPlayer.changeSong(song.getMedia());
            // Check for mute state and temporarily unmutes if its unmuted so song will play, then mutes again
            if (GlobalPlayer.getAudioMuteState()) {
                GlobalPlayer.changeAudioMuteState(false);
                GlobalPlayer.changeSong(song.getMedia());
                GlobalPlayer.changeAudioMuteState(true);
            } else {
                GlobalPlayer.changeSong(song.getMedia());
            } // If not muted, play the song normally
        });

        // Deletes a song from a playlist
        deleteButton.setOnAction(e -> {
            // get the list of current playlists
            ArrayList<Playlist> playlists = UserLib.getPlaylists();
            // Remove song from playlist object
            playlist.removeSong(song);
            // Attempt removal
            try {
                // create an ObjectOutputStream and set it to work with playlist.dat
                ObjectOutputStream excludeSongPlaylistWriter = new ObjectOutputStream(new FileOutputStream("playlist.dat"));
                // Iterate through playlists
                for (Playlist p : playlists) {
                    // If playlist is the edited playlist, write the modified playlist object to the file
                    if (p.getName().equals(playlist.getName())) {
                        excludeSongPlaylistWriter.writeObject(playlist);
                    } if (!p.getName().equals(playlist.getName())) {
                        excludeSongPlaylistWriter.writeObject(p);
                    } // If playlist is not the edited playlist, add the current playlist to the file (no changes)
                }
                // Close the ObjectOutputStream
                excludeSongPlaylistWriter.close();
                // Refresh the PlaylistSongs view
                StorePane.displayPane.getChildren().clear();
                StorePane.displayPane.getChildren().add(new PlaylistSongs(playlist));
            } catch (Exception exception) {
                exception.printStackTrace();
            } // Runs if an error occurs
        });

        // Opens the AddToPlaylistView when clicked and allows the song to be added to a playlist if owned, otherwise, handles a purchasing case
        addToPlaylistButton.setOnAction(e -> {
            // If song is owned, open add to playlist view
            if (songOwned) {
                StorePane.displayPane.getChildren().clear();
                StorePane.displayPane.getChildren().add(new AddToPlaylistView(song));
            } else {
                if (ScoreSystem.getStoredScore() >= 10) {
                    // Add to owned songs
                    Ownership.addOwnedSongs(song.getId());
                    // Decrease balance
                    ScoreSystem.updateStoredScore(ScoreSystem.getStoredScore() - 10);
                    System.out.println("Bought song");
                    // change text
                    addToPlaylistButton.setText("+");
                    // set ownership to true
                    this.songOwned = true;
                    // refresh coin count
                    StorePane.coinCount.setText("$" + ScoreSystem.getStoredScore());
                }
            } // Otherwise, run a transaction and purchase the song
        });

        // Add nodes to GridPane
        this.add(playButton, 0, 0, 1, 2);
        this.add(songName, 1, 0);
        this.add(artistName, 1, 1);
        this.add(deleteButton, 2, 0, 2, 2);
        this.add(addToPlaylistButton, 3, 0, 1, 2);

        // Set the sizing
        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
        this.setMaxWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
    }
}
