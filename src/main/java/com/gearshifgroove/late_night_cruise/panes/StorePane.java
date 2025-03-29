package com.gearshifgroove.late_night_cruise.panes;

import com.gearshifgroove.late_night_cruise.Const;
import com.gearshifgroove.late_night_cruise.CustomUIElements.PaddingBox;
import com.gearshifgroove.late_night_cruise.GlobalPlayer;
import com.gearshifgroove.late_night_cruise.LateNightCruise;
import com.gearshifgroove.late_night_cruise.ScoreSystem;
import com.gearshifgroove.late_night_cruise.panes.Store.*;
import com.gearshifgroove.late_night_cruise.scenes.MainMenuScene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

// Author(s): Christian Moloci

// Main Store pane that contains a menu bar on the left and content on the right which will change
public class StorePane extends BorderPane {
    // Create some vars
    public static Text coinCount;
    public static Image play;
    public static Image pause;
    public static ImageView mediaControl;

    // Display pane is public as we want to manipulate it from other classes
    public static Pane displayPane = new Pane();

    // Initialise some of our objects
    static {
        play = new Image(StorePane.class.getResourceAsStream("/com/gearshifgroove/late_night_cruise/play.png"), 40, 40, true, true);
        pause = new Image(StorePane.class.getResourceAsStream("/com/gearshifgroove/late_night_cruise/pause.png"), 40, 40, true, true);
        mediaControl = new ImageView(play);
    }

    // Store Pane constructor
    public StorePane() {
        LateNightCruise.mainStage.setTitle("Store");
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Side menu UI
        VBox sideMenu = new VBox();
        sideMenu.setMinWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setMaxWidth(Const.WINDOW_WIDTH/4);
        sideMenu.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Contains side menu node, important for coordinate-based nodes
        Pane sideMenuContainer = new Pane(sideMenu);
        sideMenuContainer.setMinWidth(Const.WINDOW_WIDTH/4);
        sideMenuContainer.setMaxWidth(Const.WINDOW_WIDTH/4);

        // HBox to display main menu button and coin count next to each other
        HBox menuAndCoinCount = new HBox();
        menuAndCoinCount.setMinWidth(sideMenu.getMinWidth());
        menuAndCoinCount.setMaxWidth(sideMenu.getMinWidth());

        // Main menu button
        Button back = new Button("Main Menu");
        back.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        back.setTextFill(Color.WHITE);
        back.setMinWidth(120);
        back.setMaxWidth(120);

        // Coin Count text
        coinCount = new Text("$" + ScoreSystem.getStoredScore());
        coinCount.setFill(Color.WHITE);

        // Add the main menu button and coin cound to the menuAndCoinCount HBox
        menuAndCoinCount.getChildren().addAll(back, new PaddingBox(0, 10), coinCount);

        // Menu Bar title of where the user is
        Text store = new Text("Store");
        store.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        store.setFill(Color.WHITE);

        // Search field for search queries
        TextField search = new TextField();
        search.setPromptText("Search...");
        search.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), null, null)));

        // Store home button
        Button home = new Button("Home");
        home.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        home.setTextFill(Color.WHITE);
        home.setMinWidth(sideMenu.getMinWidth());
        home.setMaxWidth(sideMenu.getMinWidth());

        // Genres Button
        Button genres = new Button("Browse Genres");
        genres.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        genres.setTextFill(Color.WHITE);
        genres.setMinWidth(sideMenu.getMinWidth());
        genres.setMaxWidth(sideMenu.getMinWidth());

        // Artists Button
        Button artists = new Button("Browse Artists");
        artists.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        artists.setTextFill(Color.WHITE);
        artists.setMinWidth(sideMenu.getMinWidth());
        artists.setMaxWidth(sideMenu.getMinWidth());

        // Songs Button
        Button songs = new Button("Browse Songs");
        songs.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        songs.setTextFill(Color.WHITE);
        songs.setMinWidth(sideMenu.getMinWidth());
        songs.setMaxWidth(sideMenu.getMinWidth());

        // Playlists Button
        Button playlistsButton = new Button("Playlists");
        playlistsButton.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        playlistsButton.setTextFill(Color.WHITE);
        playlistsButton.setMinWidth(sideMenu.getMinWidth());
        playlistsButton.setMaxWidth(sideMenu.getMinWidth());

        // Song Library Button
        Button songLibrary = new Button("Your Songs");
        songLibrary.setBackground(new Background(new BackgroundFill(Color.rgb(18, 18, 18), CornerRadii.EMPTY, Insets.EMPTY)));
        songLibrary.setTextFill(Color.WHITE);
        songLibrary.setMinWidth(sideMenu.getMinWidth());
        songLibrary.setMaxWidth(sideMenu.getMinWidth());

        // Play/Pause button
        mediaControl.setY(Const.WINDOW_HEIGHT - 45);
        mediaControl.setX(10);

        // Add Play/Pause button to a specific spot based on window height - 45
        sideMenuContainer.getChildren().addAll(mediaControl);

        // Add everything to side menu
        sideMenu.getChildren().addAll(
                menuAndCoinCount, new PaddingBox(10, 0),
                store, new PaddingBox(10, 0),
                search, new PaddingBox(10, 0),
                home, genres, artists, songs, new PaddingBox(30, 0),
                playlistsButton, songLibrary);

        // Default content for displayPane
        displayPane = new Pane(new Home());

        // Set logic and event listeners for play/pause button
        mediaControl.setOnMouseClicked(e -> {
            if (mediaControl.getImage().equals(play)) {
                GlobalPlayer.player.play();
                mediaControl.setImage(pause);
            } else {
                GlobalPlayer.player.pause();
                mediaControl.setImage(play);
            }
        });

        // Goes back to main menu when clicked
        back.setOnAction(event -> {
            GlobalPlayer.stopMedia();
            LateNightCruise.mainStage.setScene(new MainMenuScene());
        });

        // When an enter key press is recorded on the search box, run create a SearchResult
        search.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                displayPane.getChildren().clear();
                displayPane.getChildren().add(new SearchResult(search.getText()));
            }
        });

        // Goes to the store home when clicked
        home.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Home());
        });

        // Goes to the GenreView when clicked
        genres.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new GenresView());
        });

        // Goes to the Artists view when clicked
        artists.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Artists());
        });

        // Goes to the AllSongs view when clicked
        songs.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new AllSongs());
        });

        // Goes to the Playlists view when clicked
        playlistsButton.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new Playlists());
        });

        // Goes to the OwnedSongsView when clicked
        songLibrary.setOnAction(e -> {
            displayPane.getChildren().clear();
            displayPane.getChildren().add(new OwnedSongsView());
        });

        // Set the left to side menu and center to display pane for the Parent Border Pane
        this.setLeft(sideMenuContainer);
        this.setCenter(displayPane);
    }
}
