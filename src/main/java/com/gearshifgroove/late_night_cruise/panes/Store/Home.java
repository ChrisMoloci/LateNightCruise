package com.gearshifgroove.late_night_cruise.panes.Store;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.panes.Store.Data.DB;
import com.gearshifgroove.late_night_cruise.panes.Store.SubPlaylist.Ownership;
import com.gearshifgroove.late_night_cruise.panes.StorePane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class Home extends ScrollPane {
    private static ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

    public Home() {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        int mainSectionWidth = Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4;

//        ArrayList<String> ownedSongs = Ownership.getOwnedSongs();

        VBox content = new VBox();
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
//        content.setMinHeight(Const.WINDOW_HEIGHT);
        content.setMaxHeight(Const.WINDOW_HEIGHT);
        content.setMinWidth(mainSectionWidth - 15);
        content.setMaxWidth(mainSectionWidth - 15);

        Text title = new Text("Music Shop");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setTextAlignment(TextAlignment.CENTER);
        content.getChildren().addAll(new PaddingBox(80, 0), title);

        Text featuredSongsTitle = new Text("Featured songs");
        featuredSongsTitle.setFill(Color.WHITE);
        featuredSongsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        HBox featuredSongs = new HBox();
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0001").getSong("0001"), checkOwnership("0001")));
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0005").getSong("0007"), checkOwnership("0007")));
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0001").getSong("0019"), checkOwnership("0019")));
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0004").getSong("0009"), checkOwnership("0009")));

        content.getChildren().addAll(new PaddingBox(40, 0), featuredSongsTitle, featuredSongs, new PaddingBox(30, 0));

        // Quick Access Buttons (genres, artists, etc)
        Text quickAccessButtons = new Text("Find your next jam");
        HBox quickAccessButtonsBox = new HBox();

        Button artists = new Button("Artists");
        artists.setMinWidth(mainSectionWidth/3);

        Button genres = new Button("Genres");
        genres.setMinWidth(mainSectionWidth/3);

        Button topSongs = new Button("Top Songs");
        topSongs.setMinWidth(mainSectionWidth/3);

        artists.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new Artists());
        });

        genres.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new GenresView());
        });

        topSongs.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new TopSongs());
        });

        quickAccessButtonsBox.getChildren().addAll(artists, genres, topSongs);
        content.getChildren().add(quickAccessButtonsBox);

        Text featuredArtistsTitle = new Text("Featured artists");
        featuredArtistsTitle.setFill(Color.WHITE);
        featuredArtistsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        VBox featuredArtists = new VBox();
        featuredArtists.getChildren().addAll(new ArtistListing(DB.getArtists().get("0001")), new ArtistListing(DB.getArtists().get("0005")), new ArtistListing(DB.getArtists().get("0004")));
        content.getChildren().addAll(new PaddingBox(80, 0), featuredArtistsTitle, featuredArtists);

        this.setMinHeight(Const.WINDOW_HEIGHT);
        this.setMaxHeight(Const.WINDOW_HEIGHT);
        this.setMinWidth(mainSectionWidth);

        this.setContent(content);


        // Browse Albums and music
    }
    public static boolean checkOwnership(String song) {
        boolean status = false;
        for (String songToCheck : ownedSongs) {
            if (song.equals(songToCheck)) {
                status = true;
                break;
            } else {
                status = false;
            }
        }
        return status;
    }
}
