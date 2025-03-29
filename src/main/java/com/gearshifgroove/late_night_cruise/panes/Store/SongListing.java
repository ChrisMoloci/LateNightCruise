package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
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

// Author(s): Christian Moloci

// A custom node that show info about a song and actions for it
public class SongListing extends GridPane {
    // Boolean to store ownership status
    private boolean songOwned;

    // Main Constructor
    public SongListing(Song song, boolean owned) {
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
        songInfo.setPercentWidth(82);
        ColumnConstraints songActions = new ColumnConstraints();
        songActions.setPercentWidth(8);

        // Set column constraints
        this.getColumnConstraints().addAll(play, songInfo, songActions);

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
        this.add(addToPlaylistButton, 2, 0, 1, 2);

        // Set the sizing
        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
        this.setMaxWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) - 18);
    }
}
