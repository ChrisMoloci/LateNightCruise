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

// Author(s): Christian Moloci

// Home page, displays various Custom Nodes and Text nodes to create a beautiful layout
public class Home extends ScrollPane {
    // Constructor Method
    public Home() {
        // Set styling
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Main section width defines the window minus the menu bar
        int mainSectionWidth = Const.WINDOW_WIDTH - Const.WINDOW_WIDTH / 4;

        // Content box that stores everything. Add a bit of styling and sizing
        VBox content = new VBox();
        content.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        content.setMaxHeight(Const.WINDOW_HEIGHT);
        content.setMinWidth(mainSectionWidth - 15);
        content.setMaxWidth(mainSectionWidth - 15);

        // Main title, says music shop, displayed at the top of the home page below a padding box
        Text title = new Text("Music Shop");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        title.setTextAlignment(TextAlignment.CENTER);
        content.getChildren().addAll(new PaddingBox(40, 0), title);

        // Title for the featured songs section
        Text featuredSongsTitle = new Text("Featured songs");
        featuredSongsTitle.setFill(Color.WHITE);
        featuredSongsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // Featured songs section display 4 predefines song and GridBasedSongListing nodes
        HBox featuredSongs = new HBox();
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0001").getSong("0001"), Ownership.checkOwnership("0001")));
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0005").getSong("0007"), Ownership.checkOwnership("0007")));
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0001").getSong("0019"), Ownership.checkOwnership("0019")));
        featuredSongs.getChildren().add(new GridBasedSongListing(DB.getArtists().get("0004").getSong("0009"), Ownership.checkOwnership("0009")));

        // Add the features songs title and featured songs with some padding
        content.getChildren().addAll(new PaddingBox(40, 0), featuredSongsTitle, featuredSongs, new PaddingBox(30, 0));

        // Quick Access Buttons (genres, artists, all songs etc)
        HBox quickAccessButtonsBox = new HBox();

        // Artist button with styling
        Button artists = new Button("Artists");
        artists.setMinWidth(mainSectionWidth/3);
        artists.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        artists.setBorder(Border.stroke(Color.WHITE));
        artists.setTextFill(Color.WHITE);

        // Genres button with styling
        Button genres = new Button("Genres");
        genres.setMinWidth(mainSectionWidth/3);
        genres.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        genres.setBorder(Border.stroke(Color.WHITE));
        genres.setTextFill(Color.WHITE);

        // allSongs button with styling
        Button allSongs = new Button("All Songs");
        allSongs.setMinWidth(mainSectionWidth/3);
        allSongs.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));
        allSongs.setBorder(Border.stroke(Color.WHITE));
        allSongs.setTextFill(Color.WHITE);

        // Shows the Artists page (a page with every artist on the platform)
        artists.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new Artists());
        });

        // Shows the Genres page (a page with every genre on the platform)
        genres.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new GenresView());
        });

        // Shows the allSongs page (a page with every song on the platform)
        allSongs.setOnAction(e -> {
            StorePane.displayPane.getChildren().clear();
            StorePane.displayPane.getChildren().add(new AllSongs());
        });

        // Add the buttons to the quickAccessButtonsBox HBox
        quickAccessButtonsBox.getChildren().addAll(artists, genres, allSongs);

        // Add the quickAccessButtons to the content VBox
        content.getChildren().add(quickAccessButtonsBox);

        // Text for featured artists section
        Text featuredArtistsTitle = new Text("Featured artists");
        featuredArtistsTitle.setFill(Color.WHITE);
        featuredArtistsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        // VBox for the featured artists section with predefined artists added as ArtistListing nodes
        VBox featuredArtists = new VBox();
        featuredArtists.getChildren().addAll(
                new ArtistListing(DB.getArtists().get("0001")),
                new ArtistListing(DB.getArtists().get("0005")),
                new ArtistListing(DB.getArtists().get("0004")));

        // Add the featuredArtistsTitle and featuredArtists VBox to the content VBox with some padding
        content.getChildren().addAll(new PaddingBox(40, 0), featuredArtistsTitle, featuredArtists);

        // Set the dimensions of the ScrollPane so the scrolling can properly work
        this.setMinHeight(Const.WINDOW_HEIGHT);
        this.setMaxHeight(Const.WINDOW_HEIGHT);
        this.setMinWidth(mainSectionWidth);

        // Set the content of the ScrollPane as the contents VBox
        this.setContent(content);
    }
}
