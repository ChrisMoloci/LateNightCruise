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

public class SearchResult extends ScrollPane {
    public SearchResult(String query) {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        query = query.toLowerCase();

        Text title = new Text("Search results for \"" + query + "\"");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.WHITE);

        VBox content = new VBox(new PaddingBox(30, 0), title, new PaddingBox(15, 0));
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        content.setMinWidth(Const.WINDOW_WIDTH - 30);
        content.setMinHeight(Const.WINDOW_HEIGHT);

        for (Artist artist : DB.getArtists().values()) {
            if (artist.getName().toLowerCase().contains(query)) {
                if (artist.getName().toLowerCase().charAt(0) == query.charAt(0)) {
                    System.out.println("added to beginning of search results" + artist.getName());
                    content.getChildren().add(3, new ArtistListing(artist));
                } else {
                    content.getChildren().add(new ArtistListing(artist));
                }
                for (int i = 0; i < artist.getSongs().size() && i < 3; i++) {
                    content.getChildren().add(
                            new SongListing(artist.getSongs().get(i),
                                    Ownership.checkOwnership(artist.getSongs().get(i).getId())));
                }
            }
            for (Song song : artist.getSongs()) {
                if (song.getSongName().toLowerCase().contains(query)) {
                    content.getChildren().add(
                            new SongListing(song, Ownership.checkOwnership(song.getId())));
                }
            }
//            for (Song song : artist.getSongs()) {
//                if (song.getGenre().getName().toLowerCase().contains(query)) {
//                    // Check if a genre was already added to prevent adding duplicates
//                    boolean genreQueried = false;
//                    for (Node node : content.getChildren()) {
//                        try {
//                            if ((((GenreListing) node).getGenre().getName()).equals(song.getGenre().getName())) {
//                                genreQueried = true;
//                            }
//                        } catch (Exception e) {
////                            e.printStackTrace();
//                            System.out.println("Not a GenreListing Node");
//                        }
//                    }
//                    if (!genreQueried) {
//                        content.getChildren().add(new GenreListing(song.getGenre()));
//                    }
//                }
        }
        for (Genre genre : Genres.getGenres()) {
            if (genre.getName().toLowerCase().contains(query)) {
                content.getChildren().add(new GenreListing(genre));
            }
        }

        this.setMaxHeight(Const.WINDOW_HEIGHT);
        this.setMinHeight(Const.WINDOW_HEIGHT);
        this.setMinWidth(Const.WINDOW_WIDTH);

        this.setContent(content);
    }
}
