package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Genre;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.Genres;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

// Author(s): Christian Moloci

// Displays all the genre listings as a scrollable list
public class GenresView extends ScrollPane {
    public GenresView() {
        // Get all the available genres
        ArrayList<Genre> genres = Genres.getGenres();

//        this.getChildren().add(new GenreListing(Genres.getGenre("Unknown Genre")));

        // Create a VBox to store all the GenreListings in and style it
        VBox content = new VBox();
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Iterate through each genre and create a new GenreListing and add it to the content VBox
        for (Genre genre : genres) {
            content.getChildren().add(new GenreListing(genre));
        }

        // Set the content of the scroll pane to the content VBox
        this.setContent(content);

        // Set the dimensions of the scroll pane so scrolling can properly work
        this.setMinWidth(Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4);
        this.setMaxHeight(Const.WINDOW_HEIGHT);
    }
}
