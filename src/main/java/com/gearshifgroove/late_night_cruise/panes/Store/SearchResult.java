package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.*;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

// Author(s): Christian Moloci

// When a search is called, this class is instantiated and it executes a search of the songs, artists, and genres available
public class SearchResult extends ScrollPane {
    public SearchResult(String query) {
        // Set the styling of the ScrollPane
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Convert the query String to lowercase
        query = query.toLowerCase();

        // Set a title displaying what was searched for
        Text title = new Text("Search results for \"" + query + "\"");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.WHITE);

        // Create a VBox to store our search nodes in and style it
        VBox content = new VBox(new PaddingBox(30, 0), title, new PaddingBox(15, 0));
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        content.setMinWidth(Const.WINDOW_WIDTH - 30);
        content.setMinHeight(Const.WINDOW_HEIGHT);

        // Iterate through all the artists and find matching artists and songs
        for (Artist artist : DB.getArtists().values()) {
            // If an artist name contains the query
            if (artist.getName().toLowerCase().contains(query)) {
                // If the artist name begins with a query, chances are that is what they are looking for and it will be added at the top of the results
                if (artist.getName().toLowerCase().charAt(0) == query.charAt(0)) {
                    System.out.println("added to beginning of search results" + artist.getName());
                    content.getChildren().add(3, new ArtistListing(artist));
                } else {
                    content.getChildren().add(new ArtistListing(artist));
                } // Otherwise just add the search result at the end of the content VBox
                // Iterate through 3 songs of the found artist and display the songs as well to give a bit more relevant info
                for (int i = 0; i < artist.getSongs().size() && i < 3; i++) {
                    content.getChildren().add(
                            new SongListing(artist.getSongs().get(i),
                                    Ownership.checkOwnership(artist.getSongs().get(i).getId())));
                }
            }
            // Iterate through songs in each artist
            for (Song song : artist.getSongs()) {
                // If a song name contains the query, add that song to the results as well
                if (song.getSongName().toLowerCase().contains(query)) {
                    content.getChildren().add(
                            new SongListing(song, Ownership.checkOwnership(song.getId())));
                }
            }
        }
        // Iterate through all genres and add genres where the genre name contains the query
        for (Genre genre : Genres.getGenres()) {
            if (genre.getName().toLowerCase().contains(query)) {
                content.getChildren().add(new GenreListing(genre));
            }
        }

        // Set the content of the parent ScrollPane to the content VBox
        this.setContent(content);

        // set the dimensions for the parent scroll pane
        this.setMaxHeight(Const.WINDOW_HEIGHT);
        this.setMinHeight(Const.WINDOW_HEIGHT);
        this.setMinWidth(Const.WINDOW_WIDTH);
    }
}
