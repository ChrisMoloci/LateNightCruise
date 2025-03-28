package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Song;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

// Author(s): Christian Moloci

// Displays a scrollable grid of songs that and artist has
public class ArtistPage extends ScrollPane {
    public ArtistPage(Artist artist) {
        // Style the background of the scroll pane
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(0, 0, 0,0))));

        // Get the owned songs to check for ownership when generating grid based song listings
        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        // VBox to store the contents in with styling
        VBox contents = new VBox();
        contents.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Styled Text node to display the artists name
        Text artistName = new Text(artist.getName());
        artistName.setFill(Color.WHITE);
        artistName.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // A grid pane to display the grid based song listings
        GridPane songGrid = new GridPane();

        // Set 4 column constraints to add all of our grid based song listings to
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);

        // Apply the column constraints
        songGrid.getColumnConstraints().addAll(col1, col2, col3, col4);

        // Get all the artists songs and store them in an easily accessible list
        ArrayList<Song> songs = artist.getSongs();

        // Indexes for rows and columns to properly generate the grid pane of grid based song listings
        int songIndex = 0;
        int songRow = 0;
        // Generate the grid for each song
        for (Song song : songs) {
            // Check if songIndex is at it's highest possible position and start a new row if true
            if ((songIndex % 4) == 0) {
                songRow++;
                songIndex = 0;
            }
            // Set a flag to check for ownership
            boolean songOwned = false;
            for (String ownedSong : ownedSongs) {
                // If the song is owned, set the flag to true and stop checking
                if (song.getId().equals(ownedSong)) {
                    songOwned = true;
                    break;
                }
            }
            // If the song is owned create a grid based song listing and allow for it to be added to a playlist, otherwise allow purchase
            if (songOwned) {
                songGrid.add(new GridBasedSongListing(song, true), songIndex, songRow);
            } else {
                songGrid.add(new GridBasedSongListing(song, false), songIndex, songRow);
            }
            // up the song index
            songIndex++;
        }

        // Add all the nodes to content VBox
        contents.getChildren().addAll(new PaddingBox(40, 0), artistName, new PaddingBox(30, 0), songGrid);

        // Set contents VBox as the main node to scroll in the ScrollPane
        this.setContent(contents);

        // Set the dimensions for everything
        this.setMaxSize((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) + 1, Const.WINDOW_HEIGHT);
        this.setMinWidth((Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4) + 1);
    }
}
