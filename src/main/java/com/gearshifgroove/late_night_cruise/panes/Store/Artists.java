package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Artist;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

// Author(s): Christian Moloci

// Generates a page of artist listings (custom nodes to display info and actions for an artist)
public class Artists extends ScrollPane {
    public Artists() {
        // Gets all available artists
        HashMap<String, Artist> artists = DB.getArtists();

        // Create a VBox to store it is
        VBox artistsBox = new VBox();

        // Iterate through each artist and create a new artist listing in the VBox
        for (Artist artist : artists.values()) {
            artistsBox.getChildren().add(new ArtistListing(artist));
        }

        // Set the scroll pane content to the artist box
        this.setContent(artistsBox);

        // Set the sizing for the scroll pane to the window dimensions so scrolling can properly work
        this.setMaxSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
        this.setMinSize(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4, Const.WINDOW_HEIGHT);
    }
}
